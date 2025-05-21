package com.mermer.webflux.util;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Logger {


    public static void doOnNext(Object data) {
        log.info("# do onNext: {}", data);
    }

    public static void doOnRequest(Object data) {
        log.info("# do onRequest: {}", data);
    }

    public static void onNext(Object data) {
        log.info("# onNext: {}", data);
    }

    public static void onError(Throwable error) {
        log.info("# onError: {}", error.getMessage());
    }
}
