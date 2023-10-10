package com.example.bookBucketBackend.repository;

import com.example.bookBucketBackend.Constants;
import com.example.bookBucketBackend.entity.OrderEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderRepository {
    private final MongoTemplate mongoTemplate;

    public OrderEntity getLiveOrderByUser(String userId, Constants.OrderType orderType) {
        Criteria criteria = Criteria
                .where("userId").is(userId)
                .and("isSubmitted").is(false)
                .and("isDeleted").is(false)
                .and("orderType").is(orderType);
        return mongoTemplate.findOne(new Query(criteria), OrderEntity.class);
    }

    public void addOrder(OrderEntity orderEntity) {
        mongoTemplate.save(orderEntity);
    }

    public void updateOrder(OrderEntity orderEntity) {
        mongoTemplate.save(orderEntity);
    }

    public List<OrderEntity> getAllOrders(Constants.OrderType orderType) {
        Criteria criteria = Criteria
                .where("isSubmitted").is(false)
                .and("isDeleted").is(false)
                .and("orderType").is(orderType);
        return mongoTemplate.find(new Query(criteria), OrderEntity.class);
    }
}
