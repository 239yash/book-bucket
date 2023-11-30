package com.example.bookBucketBackend.controller.admin;


import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.dto.model.OrderModel;
import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.service.admin.AdminService;
import com.example.bookBucketBackend.service.books.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
    @Operation(summary = "order listing", description = "Get all orders in a single API(Admin usage only)")
    @Parameters({
            @Parameter(name = "orderType", description = "order type - buy or sell", required = true),
            @Parameter(name = "userId", description = "user id of the user for which orders are to be fetched"),
            @Parameter(name = "orderId", description = "order id for fetching a specific order"),
            @Parameter(name = "includeExpired", description = "boolean field for including expired orders too")
    })
    public ResponseEntity<?> getAllOrders(@RequestParam() Constants.OrderType orderType,
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
    @Operation(summary = "Add book", description = "Add a new book in the database(Admin usage only)")
    public ResponseEntity<?> addBook(@RequestBody BookList.Book book) {
        if (bookService.addBook(book)) {
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail");
    }

    @PutMapping("/change-status")
    @Operation(summary = "Order status change", description = "Api for changing order status(Admin usage only)")
    public ResponseEntity<?> changeOrderStatus(@RequestBody OrderModel order) {
        if (order.getOrderId() == null) {
            return ResponseEntity.badRequest().body("Order Id is required!");
        }
        return ResponseEntity.ok(adminService.changeOrderStatus(order));
    }
}
