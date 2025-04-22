package com.mermer.webflux;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;

@Slf4j
public class HelloReactor {

    public static void main(String[] args) {
        Mono.just("Hello Reactor")
                .subscribe(data -> log.info("# emitted data:{}", data));

        Mono.empty()
                .subscribe(
                        data -> log.info("# emitted data : data")
                        , error -> log.info("# emitted data occurred error")
                        , () -> log.info("# emitted onComplete signal")
                );
        Flux<String> sequence = Flux.just("hello", "reactor");

        sequence
                .map(data -> data.toUpperCase())
                .subscribe(data -> log.info(data))
        ;


    }
}
