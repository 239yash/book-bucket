package com.example.bookBucketBackend.service.user;

import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.entity.OrderEntity;
import com.example.bookBucketBackend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookOrderService {
    private final OrderRepository orderRepository;

    public boolean createOrder(OrderModel orderData) {
        String userId = orderData.getUserId();
        if (orderRepository.getLiveOrderByUser(userId) != null) {
            return false;
        }
        OrderEntity orderEntity = getOrderEntity(orderData);
        orderRepository.addOrder(orderEntity);
        return true;
    }

    private OrderEntity getOrderEntity(OrderModel orderData) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderStatus(Constants.OrderStatus.NEW);
        orderEntity.setOrderType(Constants.OrderType.BUY);
        orderEntity.setUserId(orderData.getUserId());
        orderEntity.setBooks(orderData.getBooks());
        orderEntity.setSubmitted(false);
        orderEntity.setDeleted(false);
        return orderEntity;
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

    public boolean deleteOrder(OrderModel orderModel) {
        String userId = orderModel.getUserId();
        OrderEntity orderEntity = orderRepository.getLiveOrderByUser(userId);
        if (orderEntity == null) {
            return false;
        }
        orderEntity.setDeleted(true);
        orderRepository.updateOrder(orderEntity);
        return true;
    }

    private void updateOrderEntityAgainstRequestData(OrderEntity orderEntity, OrderModel orderData) {
        orderEntity.setBooks(orderData.getBooks().isEmpty() ? orderEntity.getBooks() : orderData.getBooks());
    }
}
