package com.mermer.webflux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Slf4j
public class ColdSequenceExample {

    public static void main(String[] args) {
        Flux<String> coldFlux = Flux.fromIterable(Arrays.asList("RED", "YELLOW", "PINK"))
                .map(String::toLowerCase)
                ;

        coldFlux.subscribe(country -> log.info("# Subscriber1: {}", country));
        log.info("-----------------------------------------------------------");
        coldFlux.subscribe(country -> log.info("# Subscriber1: {}", country));
    }

}
