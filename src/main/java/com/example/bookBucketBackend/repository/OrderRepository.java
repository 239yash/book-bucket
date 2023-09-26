package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderRepository {
    private final MongoTemplate mongoTemplate;

    public OrderEntity getLiveOrderByUser(String userId) {
        Criteria criteria = Criteria
                .where("userId").is(userId)
                .and("isSubmitted").is(false)
                .and("isDeleted").is(false);
        return mongoTemplate.findOne(new Query(criteria), OrderEntity.class);
    }

    public void addOrder(OrderEntity orderEntity) {
        mongoTemplate.save(orderEntity);
    }
}
