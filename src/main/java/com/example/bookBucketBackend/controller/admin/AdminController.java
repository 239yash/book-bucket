package com.example.bookBucketBackend.controller.admin;


import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.service.admin.AdminService;
import com.example.bookBucketBackend.service.books.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth/admin")
@Slf4j
@PreAuthorize("hasAuthority('ROLE_ADMIN')")

public class AdminController {
    private final AdminService adminService;
    private final BookService bookService;

    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(@RequestParam(required = false) Constants.OrderType orderType,
                                          @RequestParam(required = false) String userId,
                                          @RequestParam(required = false) String orderId,
                                          @RequestParam(required = false) Boolean includeExpired) {
        if (userId == null && orderId == null) {
            return ResponseEntity.badRequest().body("Order Id or User Id is required!");
        }
        if (includeExpired == null) {
            includeExpired = false;
        }
        return ResponseEntity.ok(adminService.getAllOrders(orderType, userId, orderId, includeExpired));
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestBody BookList.Book book) {
        if (bookService.addBook(book)) {
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail");
    }

    @PutMapping("/change-status")
    public ResponseEntity<?> changeOrderStatus(@RequestBody OrderModel order) {
        if (order.getOrderId() == null) {
            return ResponseEntity.badRequest().body("Order Id is required!");
        }
        return ResponseEntity.ok(adminService.changeOrderStatus(order));
    }
}
