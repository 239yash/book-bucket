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
}
