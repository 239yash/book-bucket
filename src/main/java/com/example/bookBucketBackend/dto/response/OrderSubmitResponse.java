package com.example.bookBucketBackend.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderSubmitResponse {
    private boolean success;
    private List<BookOrderResult> data;

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class BookOrderResult {
        private String bookId;
        private String bookName;
        private boolean success;
    }

}
