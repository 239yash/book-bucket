package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.entity.BookEntity;

import java.util.List;

public interface BookRepository {
     void addBook(BookEntity bookEntity);

     List<BookEntity> getAllBooks();

     BookEntity getBook(String bookId);

     void updateBook(BookEntity bookEntity);
}
