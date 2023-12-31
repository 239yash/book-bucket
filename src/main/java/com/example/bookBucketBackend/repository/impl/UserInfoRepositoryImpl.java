package com.example.bookBucketBackend.repository.impl;

import com.example.bookBucketBackend.entity.UserInfo;
import com.example.bookBucketBackend.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInfoRepositoryImpl implements UserInfoRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<UserInfo> findByEmail(String email) {
        Criteria criteria = Criteria
                .where("emailAddress").is(email);
        return Optional.ofNullable(mongoTemplate.findOne(new Query(criteria), UserInfo.class));
    }

    @Override
    public void save(UserInfo userInfo) {
        mongoTemplate.save(userInfo);
    }
}
