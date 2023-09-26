package com.example.bookBucketBackend.controller.user;

import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.service.user.BookOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
@Slf4j
public class BookOrderController {
    private final BookOrderService bookOrderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderModel orderData) {
        if (orderData.getUserId() == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderService.createOrder(orderData));
    }
}
