package com.example.bookBucketBackend.service.admin;


import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.entity.OrderEntity;
import com.example.bookBucketBackend.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class AdminService {
    private final OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders(Constants.OrderType orderType, String userId) {
        return orderRepository.getAllOrders(orderType, userId);
    }
}
