package com.example.bookBucketBackend.controller.book;


import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.service.books.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping("/list")
    public BookList getBooksList() {
        return bookService.getBooksList();
    }

    @GetMapping("/{bookId}")
    public BookList.Book getBook(@PathVariable String bookId) {
        return bookService.getBook(bookId);
    }
}
