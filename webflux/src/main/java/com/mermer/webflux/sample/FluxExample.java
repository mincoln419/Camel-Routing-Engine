package com.mermer.webflux.sample;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class FluxExample {

    public static void main(String[] args) throws InterruptedException {
        Flux<Integer> remainderFlux = Flux.just(6, 9, 12)
                .map(num -> num % 2)
                //.delaySequence(Duration.ofMillis(1000L))
                ;

        Flux<Integer> multiplyFlux = Flux.fromArray(new Integer[]{1, 6, 7, 9})
                .filter(num -> num > 6)
                .map(num -> num * 2)
                //.delaySequence(Duration.ofMillis(2000L))
                ;

        Flux<String> concatFlux = Mono.justOrEmpty("another mono")
                        .concatWith(Mono.justOrEmpty("Jobs"));
        concatFlux.subscribe(data -> log.info("# result: {}", data));

        Flux.concat(
                Flux.just("Venus"), Flux.just("Earth"), Flux.just("Mars"))
                        .collectList()
                        .subscribe(planetList -> log.info("# Solar System: {}", planetList));


        multiplyFlux.subscribe(multiply -> log.info("# multiply: {}", multiply));
        remainderFlux.subscribe(remainder -> log.info("# remainder : {}", remainder));
        //Thread.sleep(10000L);
    }
}
