package com.example.bookBucketBackend.service.books;

import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.entity.BookEntity;
import com.example.bookBucketBackend.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    public BookList getBooksList() {
        List<BookEntity> data = bookRepository.getAllBooks();
        List<BookList.Book> books = new ArrayList<>();
        BookList.Book bookData = new BookList.Book();
        for (BookEntity book : data) {
            bookData.setBookName(book.getBookName());
            bookData.setBookId(book.getBookId());
            bookData.setYear(book.getYear());
            bookData.setBranch(book.getBranch());
            bookData.setCountsAvailable(book.getCountsAvailable());
            books.add(bookData);
        }
        return BookList.builder().books(books).build();
    }

    public BookList.Book getBook(String bookId) {
        BookEntity book = bookRepository.getBook(bookId);
        BookList.Book bookData = new BookList.Book();
        bookData.setBookName(book.getBookName());
        bookData.setBookId(book.getBookId());
        bookData.setYear(book.getYear());
        bookData.setBranch(book.getBranch());
        bookData.setCountsAvailable(book.getCountsAvailable());
        return bookData;
    }

    public boolean addBook(BookList.Book book) {
        try {
            BookEntity bookEntity = new BookEntity();
            bookEntity.setBookName(book.getBookName());
            bookEntity.setYear(book.getYear());
            bookEntity.setBranch(book.getBranch());
            bookEntity.setCountsAvailable(book.getCountsAvailable());
            bookEntity.setSemester(book.getSemester());
            bookRepository.addBook(bookEntity);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
