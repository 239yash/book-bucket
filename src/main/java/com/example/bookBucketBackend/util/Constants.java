package com.example.bookBucketBackend.util;

public class Constants {
    public enum OrderType {
        BUY, SELL
    }

    public enum OrderStatus {
        NEW, IN_PROCESS, COMPLETED, REJECTED, ON_HOLD
    }
}
