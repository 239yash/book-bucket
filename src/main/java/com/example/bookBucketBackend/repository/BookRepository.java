package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.entity.BookEntity;

import java.util.List;

public interface BookRepository {
    public void addBook(BookEntity bookEntity);

    public List<BookEntity> getAllBooks();

    public BookEntity getBook(String bookId);

    public void updateBook(BookEntity bookEntity);
}
