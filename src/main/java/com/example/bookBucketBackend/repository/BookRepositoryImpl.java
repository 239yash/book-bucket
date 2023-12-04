package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.entity.BookEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final MongoTemplate mongoTemplate;

    public void addBook(BookEntity bookEntity) {
        mongoTemplate.save(bookEntity);
    }

    public List<BookEntity> getAllBooks() {
        return mongoTemplate.findAll(BookEntity.class);
    }

    public BookEntity getBook(String bookId) {
        Criteria criteria = Criteria.where("bookId").is(bookId);
        Query query = new Query(criteria);
        return mongoTemplate.findOne(query, BookEntity.class);
    }

    public void updateBook(BookEntity bookEntity) {
        mongoTemplate.save(bookEntity);
    }
}
