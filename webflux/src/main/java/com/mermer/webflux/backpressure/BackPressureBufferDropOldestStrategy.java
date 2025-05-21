package com.mermer.webflux.backpressure;

import com.mermer.webflux.util.Logger;
import com.mermer.webflux.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BackPressureBufferDropOldestStrategy {

    public static void main(String[] args) {

        Flux.interval(Duration.ofMillis(300L))
                .doOnNext(data -> log.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(2, dropped-> log.info("# overflow & dropped : {}", dropped),
                        BufferOverflowStrategy.DROP_OLDEST)
                .doOnNext(data -> log.info("# emitted by Buffer: {}", data))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(
                        data -> {
                            TimeUtils.sleep(1000L);
                            Logger.onNext(data);
                        }, error -> Logger.onError(error)
                );
        TimeUtils.sleep(3000L);
    }
}
