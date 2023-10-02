package com.example.bookBucketBackend.controller.user;

import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.service.user.BookOrderOrRentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders/buy")
@Slf4j
public class BookBuyController {
    private final BookOrderOrRentService bookOrderOrRentService;

    @PostMapping
    public ResponseEntity<?> createOrderDraft(@RequestBody OrderModel orderModel) {
        if (orderModel.getUserId() == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.createOrder(orderModel));
    }

    @PutMapping
    public ResponseEntity<?> editOrderDraft(@RequestBody OrderModel orderModel) {
        if (orderModel.getUserId() == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.editOrder(orderModel));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrderDraft(@RequestBody OrderModel orderModel) {
        String userId = orderModel.getUserId();
        if (userId == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.deleteOrder(userId));
    }

    @GetMapping
    public ResponseEntity<?> getOrderDraft(@RequestParam String userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.getOrder(userId));
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitOrderDraft(@RequestBody OrderModel orderModel) {
        String userId = orderModel.getUserId();
        if (userId == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.submitOrder(userId));
    }
}
