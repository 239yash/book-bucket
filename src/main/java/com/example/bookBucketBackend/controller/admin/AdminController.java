package com.example.bookBucketBackend.controller.admin;


import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.entity.OrderEntity;
import com.example.bookBucketBackend.service.admin.AdminService;
import com.example.bookBucketBackend.service.books.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth/admin")
@Slf4j
@PreAuthorize("hasAuthority('ROLE_ADMIN')")

public class AdminController {
    private final AdminService adminService;
    private final BookService bookService;

    @GetMapping("/orders")
    public List<OrderEntity> getAllOrders(@RequestParam Constants.OrderType orderType,
                                          @RequestParam(required = false) String userId) {
        log.info("In Admin Controller");
        return adminService.getAllOrders(orderType, userId);
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody BookList.Book book) {
        if (bookService.addBook(book)) {
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail");
    }
}
