package com.example.bookBucketBackend.entity;

import com.example.bookBucketBackend.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
public class UserEntity {
    @Id
    private String userId;
    private String userName;
    private Constants.UserType userType;
}
