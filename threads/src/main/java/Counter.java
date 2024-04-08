import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final AtomicInteger count;

    public Counter() {
        this.count = new AtomicInteger(0);
    }

    public int get() {
        return count.get();
    }

    public void increment() {
        count.getAndIncrement();
    }

}
