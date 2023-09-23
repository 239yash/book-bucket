package com.example.bookBucketBackend.controller.book;


import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.service.books.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody BookList.Book book) {
        if (bookService.addBook(book)) {
            return ResponseEntity.ok("Success");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail");
    }
}
