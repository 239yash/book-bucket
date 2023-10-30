package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.entity.UserInfo;
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
    public Optional<UserInfo> findByName(String userName) {
        Criteria criteria = Criteria
                .where("name").is(userName);
        return Optional.ofNullable(mongoTemplate.findOne(new Query(criteria), UserInfo.class));
    }

    @Override
    public void save(UserInfo userInfo) {
        mongoTemplate.save(userInfo);
    }
}
