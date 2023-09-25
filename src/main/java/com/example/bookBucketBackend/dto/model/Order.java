package com.example.bookBucketBackend.dto.model;

import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.dto.response.BookList;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String userId;
    private boolean isSubmitted;
    private boolean isDeleted;
    private Constants.OrderType orderType;
    private List<BookList.Book> books;
}
