package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository {
    Optional<UserInfo> findByName(String username);
}
