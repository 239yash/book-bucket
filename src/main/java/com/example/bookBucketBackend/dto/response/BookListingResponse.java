package com.example.bookBucketBackend.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class BookListingResponse {
    private List<Book> books;

    @Builder
    @Getter
    @Setter
    @Data
    public static class Book {
        private String bookId;
        private String bookName;
        private String branch;
        private int semester;
        private int year;
        private int countsAvailable;
    }
}
