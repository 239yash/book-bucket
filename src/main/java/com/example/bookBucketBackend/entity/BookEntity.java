package com.example.bookBucketBackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public class BookEntity {
    @Id
    private String bookId;
    private String bookName;
    private String branch;
    private int semester;
    private int year;
    private int countsAvailable;
}
