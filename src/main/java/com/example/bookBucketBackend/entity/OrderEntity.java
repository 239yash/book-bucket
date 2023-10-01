package com.example.bookBucketBackend.entity;

import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.dto.response.BookList;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
public class OrderEntity {
    @Id
    private String id;
    private String userId;
    private String orderId;
    private boolean isSubmitted;
    private boolean isDeleted;
    private Constants.OrderType orderType;
    private List<BookList.Book> books;
    private Constants.OrderStatus orderStatus;
}
