package com.example.bookBucketBackend.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookList {
    private List<Book> books;

    @Builder
    @Getter
    @Setter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Book {
        private String bookId;
        private String bookName;
        private String branch;
        private int semester;
        private int year;
        private int countsAvailable;
    }

    @Builder
    @Getter
    @Setter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookOrder {
        private String bookId;
        private int count;
    }
}
