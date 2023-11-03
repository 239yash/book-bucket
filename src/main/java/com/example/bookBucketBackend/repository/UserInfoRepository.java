package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository {
    Optional<UserInfo> findByEmail(String email);

    void save(UserInfo userInfo);
}
