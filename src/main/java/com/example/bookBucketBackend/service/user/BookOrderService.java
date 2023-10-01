package com.example.bookBucketBackend.service.user;

import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.dto.response.OrderSubmitResponse;
import com.example.bookBucketBackend.entity.BookEntity;
import com.example.bookBucketBackend.entity.OrderEntity;
import com.example.bookBucketBackend.repository.BookRepository;
import com.example.bookBucketBackend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class BookOrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public boolean createOrder(OrderModel orderData) {
        String userId = orderData.getUserId();
        if (orderRepository.getLiveOrderByUser(userId) != null) {
            return false;
        }
        OrderEntity orderEntity = getNewBuyOrderEntity(orderData);
        orderRepository.addOrder(orderEntity);
        return true;
    }

    public boolean editOrder(OrderModel orderData) {
        String userId = orderData.getUserId();
        OrderEntity orderEntity = orderRepository.getLiveOrderByUser(userId);
        if (orderEntity == null) {
            return false;
        }
        updateOrderEntityAgainstRequestData(orderEntity, orderData);
        orderRepository.updateOrder(orderEntity);
        return true;
    }

    public boolean deleteOrder(String userId) {
        OrderEntity orderEntity = orderRepository.getLiveOrderByUser(userId);
        if (orderEntity == null) {
            return false;
        }
        orderEntity.setDeleted(true);
        orderRepository.updateOrder(orderEntity);
        return true;
    }

    private OrderEntity getNewBuyOrderEntity(OrderModel orderData) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(Constants.OrderStatus.NEW);
        orderEntity.setOrderType(Constants.OrderType.BUY);
        orderEntity.setUserId(orderData.getUserId());
        orderEntity.setBooks(orderData.getBooks());
        orderEntity.setSubmitted(false);
        orderEntity.setDeleted(false);
        return orderEntity;
    }

    private void updateOrderEntityAgainstRequestData(OrderEntity orderEntity, OrderModel orderData) {
        orderEntity.setBooks(orderData.getBooks().isEmpty() ? orderEntity.getBooks() : orderData.getBooks());
    }

    public Object submitOrder(String userId) {
        OrderEntity orderEntity = orderRepository.getLiveOrderByUser(userId);
        String orderId = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "").substring(19);
        if (orderEntity == null) {
            return "Draft not found";
        }
        if (orderEntity.getBooks().isEmpty()) {
            return "No books found in draft, Please add books";
        }
        orderEntity.setOrderId(orderId);
        orderEntity.setSubmitted(true);
        List<BookList.Book> books = orderEntity.getBooks();
        List<OrderSubmitResponse.BookOrderResult> bookOrderResults = new ArrayList<>();
        for (BookList.Book book : books) {
            String bookId = book.getBookId();
            if (bookId == null) {
                continue;
            }
            BookEntity bookEntity = bookRepository.getBook(bookId);
            OrderSubmitResponse.BookOrderResult bookOrderResult = new OrderSubmitResponse.BookOrderResult();
            bookOrderResult.setBookId(bookId);
            bookOrderResult.setBookName(bookEntity.getBookName());
            if (bookEntity.getCountsAvailable() <= 0) {
                bookOrderResult.setSuccess(false);
            } else {
                int countsAvailable = bookEntity.getCountsAvailable() - 1;
                bookEntity.setCountsAvailable(countsAvailable);
                bookRepository.updateBook(bookEntity);
                bookOrderResult.setSuccess(true);
            }
            bookOrderResults.add(bookOrderResult);
        }
        OrderSubmitResponse orderSubmitResponse = new OrderSubmitResponse();
        orderSubmitResponse.setSuccess(true);
        orderSubmitResponse.setData(bookOrderResults);
        orderRepository.updateOrder(orderEntity);
        return orderSubmitResponse;
    }
}
