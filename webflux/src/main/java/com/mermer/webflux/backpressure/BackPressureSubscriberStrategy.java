package com.mermer.webflux.backpressure;

import com.mermer.webflux.util.Logger;
import com.mermer.webflux.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

@Slf4j
public class BackPressureSubscriberStrategy {

    public static int count = 0;
    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnNext(Logger::doOnNext)
                .doOnRequest(Logger::doOnRequest)
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(2);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        count++;
                        Logger.onNext(value);
                        if(count == 2){
                            TimeUtils.sleep(2000L);
                            request(2);
                            count = 0;
                        }

                    }
                });
    }
}
