package com.example.bookBucketBackend.service.books;

import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public BookList getBooksList() {
        bookRepository.addBook();
        return null;
    }
}
