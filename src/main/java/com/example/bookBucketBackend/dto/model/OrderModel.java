package com.example.bookBucketBackend.dto.model;

import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.util.Constants;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private String orderId;
    private String userId;
    private boolean isSubmitted;
    private boolean isDeleted;
    private Constants.OrderType orderType;
    private List<BookList.BookOrder> books;
    private Constants.OrderStatus orderStatus;
}
