package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.entity.OrderEntity;
import com.example.bookBucketBackend.util.Constants;

import java.util.List;

public interface OrderRepo {
    OrderEntity getLiveOrderByUser(String userId, Constants.OrderType orderType);

    void addOrder(OrderEntity orderEntity);

    void updateOrder(OrderEntity orderEntity);

    List<OrderEntity> getAllOrders(Constants.OrderType orderType,
                                   String userId,
                                   String orderId,
                                   Boolean includeExpired);
}
