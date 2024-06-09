package model;

import java.util.concurrent.Callable;

public class FactorialTask implements Callable<Integer> {

    private final Integer[] array;
    private final int start;
    private final int end;

    public FactorialTask(final Integer[] array, final int start, final int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() {
        int result = 1;
        for (int i = start; i < end; i++) {
            result *= array[i];
        }
        return result;
    }

}
