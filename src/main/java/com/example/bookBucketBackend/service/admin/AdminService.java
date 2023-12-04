package com.example.bookBucketBackend.service.admin;


import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.entity.OrderEntity;
import com.example.bookBucketBackend.repository.OrderRepository;
import com.example.bookBucketBackend.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class AdminService {
    private final OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders(Constants.OrderType orderType, String userId, String orderId, Boolean includeExpired) {
        return orderRepository.getAllOrders(orderType, userId, orderId, includeExpired);
    }

    public Object changeOrderStatus(OrderModel order) {
        String orderId = order.getOrderId();
        List<OrderEntity> orderEntities = orderRepository.getAllOrders(order.getOrderType(), null, orderId, false);
        if (orderEntities.isEmpty()) {
            return "No such order found!";
        }
        OrderEntity orderEntity = orderEntities.get(0);
        Constants.OrderStatus orderStatus = orderEntity.getOrderStatus();
        if (checkOrderTerminalStatus(orderStatus)) {
            return "Order is already in terminal state, Cannot change the status";
        }
        orderEntity.setOrderStatus(order.getOrderStatus());
        orderRepository.updateOrder(orderEntity);
        return "Order status updated!";
    }

    private boolean checkOrderTerminalStatus(Constants.OrderStatus orderStatus) {
        return orderStatus == Constants.OrderStatus.REJECTED || orderStatus == Constants.OrderStatus.COMPLETED;
    }
}
