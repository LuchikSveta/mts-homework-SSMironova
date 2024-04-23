package service;

import model.CounterEntity;

import java.util.ArrayList;
import java.util.List;

public interface CounterMethod {

    static void calculate(final int n, final int threadNum) throws InterruptedException {
        CounterEntity counter = new CounterEntity();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {
            var thread = new Thread(() -> {
                for (int j = 0; j < n; j++) {
                    counter.increment();
                }
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread:threads) {
            thread.join();
        }
        System.out.println("Результат counter: " + counter.get());
    }

}
