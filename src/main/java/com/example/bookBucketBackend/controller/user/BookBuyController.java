package com.example.bookBucketBackend.controller.user;

import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.service.user.BookOrderOrRentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth/buy")
@Slf4j
@PreAuthorize("hasAuthority('ROLE_USER')")
public class BookBuyController {
    private final BookOrderOrRentService bookOrderOrRentService;

    @PostMapping
    @Operation(summary = "create book buy order draft")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "order model with details like books, orderType, userId")
    public ResponseEntity<?> createOrderDraft(@RequestBody OrderModel orderModel) {
        if (orderModel.getUserId() == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.createOrder(orderModel, Constants.OrderType.BUY));
    }

    @PutMapping
    @Operation(summary = "edit book buy order draft")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "order model with details like books, orderType, userId")
    public ResponseEntity<?> editOrderDraft(@RequestBody OrderModel orderModel) {
        if (orderModel.getUserId() == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.editOrder(orderModel, Constants.OrderType.BUY));
    }

    @DeleteMapping
    @Operation(summary = "delete book buy order draft")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "order model with details like books, orderType, userId")
    public ResponseEntity<?> deleteOrderDraft(@RequestBody OrderModel orderModel) {
        String userId = orderModel.getUserId();
        if (userId == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.deleteOrder(userId, Constants.OrderType.BUY));
    }

    @GetMapping
    @Operation(summary = "get book buy order draft")
    @Parameters({
            @Parameter(name = "userId", description = "only one single active order draft exists for an user, so userId is used as an identifier", required = true)
    })
    public ResponseEntity<?> getOrderDraft(@RequestParam String userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.getOrder(userId, Constants.OrderType.BUY));
    }

    @PostMapping("/submit")
    @Operation(summary = "submit book buy order draft", description = "API for converting draft to order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "order model with details like books, orderType, userId")
    public ResponseEntity<?> submitOrderDraft(@RequestBody OrderModel orderModel) {
        String userId = orderModel.getUserId();
        if (userId == null) {
            return ResponseEntity.badRequest().body("Please pass valid userId");
        }
        return ResponseEntity.ok(bookOrderOrRentService.submitOrder(userId, Constants.OrderType.BUY));
    }
}
