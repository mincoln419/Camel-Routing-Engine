package com.mermer.webflux.backpressure;

import com.mermer.webflux.util.Logger;
import com.mermer.webflux.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BackPressureDropStrategy {

    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureDrop(dropped -> log.info("# dropped : {}", dropped))
                .doOnNext(Logger::doOnNext)
                .publishOn(Schedulers.parallel())
                .subscribe(
                        data -> {
                            TimeUtils.sleep(3L);
                            Logger.onNext(data);
                        }, error -> Logger.onError(error)
                );
        TimeUtils.sleep(3000L);
    }
}
