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

    public List<OrderEntity> getAllOrders(Constants.OrderType orderType,
                                          String userId,
                                          String orderId,
                                          Boolean includeExpired) {
        Criteria criteria = Criteria.where("orderType").is(orderType);
        if (!includeExpired) {
            criteria.and("isSubmitted").is(false)
                    .and("isDeleted").is(false);
        }
        if (userId != null) {
            criteria.and("userId").is(userId);
        } else {
            criteria.and("orderId").is(orderId);
        }
        return mongoTemplate.find(new Query(criteria), OrderEntity.class);
    }
}
