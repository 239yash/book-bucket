package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.dto.response.BookList;
import com.example.bookBucketBackend.entity.BookEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookRepository {
    private final MongoTemplate mongoTemplate;

    public void addBook() {
        BookList.Book book = new BookList.Book();
        book.setBookName("xyz");

        mongoTemplate.save(book);
    }

    public List<BookEntity> getAllBooks() {
        return mongoTemplate.findAll(BookEntity.class);
    }

    public BookEntity getBook(String bookId) {
        Criteria criteria = Criteria.where("bookId").is(bookId);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, BookEntity.class);
    }
}
