package com.example.bookBucketBackend.controller.admin;


import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.entity.OrderEntity;
import com.example.bookBucketBackend.service.admin.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin/orders")
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public List<OrderEntity> getAllOrders(@RequestParam Constants.OrderType orderType,
                                          @RequestParam(required = false) String userId) {
        return adminService.getAllOrders(orderType, userId);
    }
}
