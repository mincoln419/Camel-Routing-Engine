package com.mermer.webflux.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeUtils {

    public static void sleep(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread Sleep Exception");
        }
    }
}
