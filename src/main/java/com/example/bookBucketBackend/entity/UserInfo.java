package com.example.bookBucketBackend.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    private String userId;
    private String roles;
    private String emailAddress;
    private String password;
}
