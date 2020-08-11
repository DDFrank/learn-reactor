package io.frank.learn.core;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @author jinjunliang
 */
public class SchedulerDemo {
    public static void main(String[] args) throws Exception {
        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> {
                    System.out.println("first map happens " + Thread.currentThread().getName());
                    return 10 + i;
                })
                .publishOn(s)
                .map(i -> {
                    System.out.println("second map happens " + Thread.currentThread().getName());
                    return "value " + i;
                });

        new Thread(() -> {
            Disposable d = flux.subscribe(
                    i -> System.out.println("subscription happens " + Thread.currentThread().getName()),
                    e -> e.printStackTrace(),
                    () -> System.out.println("complete")
            );
        }).run();

        Thread.sleep(1000);
    }
}
