package edu.miu.eshop.eshopadmin.utils;

import java.time.Instant;

public class IdGenerator {
    public static String getTimeStamp(String type) {
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        return type + timeStampMillis;
    }
}
