package model;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterEntity {

    private final AtomicInteger count;

    public CounterEntity() {
        this.count = new AtomicInteger(0);
    }

    public int get() {
        return count.get();
    }

    public void increment() {
        count.getAndIncrement();
    }

}
