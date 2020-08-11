package io.frank.learn.core;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author jinjunliang
 */
public class SchedulerDemo2 {
    public static void main(String[] args) {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> {
                    System.out.println("first map happens " + Thread.currentThread().getName());
                    return 10 + i;
                })
                .subscribeOn(s)
                .map(i -> {
                    System.out.println("second map happens " + Thread.currentThread().getName());
                    return "value " + i;
                });

        new Thread(() -> flux.subscribe(System.out::println)).run();
    }
}
