package com.example.bookBucketBackend.controller.book;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
@Slf4j
public class BookController {

    @GetMapping("/list")
    public List<?> getBooksList() {
        return new ArrayList<>();
    }
}
