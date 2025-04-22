package com.mermer.webflux;


import ch.qos.logback.core.util.TimeUtil;
import com.mermer.webflux.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
public class HotSequenceExample {

    public static void main(String[] args) {
        Flux<String> hotFlux = Flux.fromStream(Stream.of("Single A", "Single B", "Single C", "Single D", "Single E"))
                .delayElements(Duration.ofMillis(1000L)).share()
                ;

        hotFlux.subscribe(country -> log.info("# Subscriber1: {}", country));
        TimeUtils.sleep(2000L);
        log.info("-----------------------------------------------------------");
        hotFlux.subscribe(country -> log.info("# Subscriber2: {}", country));


        TimeUtils.sleep(6000L);
    }
}
