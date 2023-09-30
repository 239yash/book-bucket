package com.example.bookBucketBackend.controller.user;

import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.service.user.BookOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
@Slf4j
public class BookOrderController {
    private final BookOrderService bookOrderService;

    @PostMapping
    public ResponseEntity<?> createOrderDraft(@RequestBody OrderModel orderModel) {
        if (orderModel.getUserId() == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderService.createOrder(orderModel));
    }

    @PutMapping
    public ResponseEntity<?> editOrderDraft(@RequestBody OrderModel orderModel) {
        if (orderModel.getUserId() == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderService.editOrder(orderModel));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrderDraft(@RequestBody OrderModel orderModel) {
        String userId = orderModel.getUserId();
        if (userId == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderService.deleteOrder(userId));
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitOrder(@RequestBody OrderModel orderModel) {
        String userId = orderModel.getUserId();
        if (userId == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderService.submitOrder(userId));
    }
}
