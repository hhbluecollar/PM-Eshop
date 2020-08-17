package edu.miu.eshop.eshopuser.utils;

import java.time.Instant;

public class IdGenerator {
    public static String getTimeStamp() {
        Instant instant = Instant.now();
        long timeStampMillis = instant.toEpochMilli();
        return "CU" + timeStampMillis;
    }
}
