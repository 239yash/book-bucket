package com.example.bookBucketBackend;

public class Constants {
    public enum OrderType {
        BUY, SELL
    }

    public enum OrderStatus {
        NEW, IN_PROCESS, COMPLETED, REJECTED, ON_HOLD
    }

    public enum UserType {
        USER, ADMIN
    }
}
