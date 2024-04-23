package service;

import model.FactorialTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public interface FactorialMethod {

    static void calculate(final int n, final int threadNum) throws ExecutionException, InterruptedException {
        Integer[] list = new Integer[n];
        for (int i = 1; i <= n; i++) {
            list[i - 1] = i;
        }
        int chunkSize = n / threadNum;

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        List<Future<Integer>> futures  = new ArrayList<>();
        for(int i = 0; i < threadNum; i++){
            int startIndex = i * chunkSize;
            int endIndex = (i == threadNum - 1) ? n : startIndex + chunkSize;
            futures.add(executor.submit(new FactorialTask(list, startIndex, endIndex)));
        }

        int answer = 1;
        for (Future<Integer> future : futures){
            answer *= future.get();
        }
        executor.shutdown();
        System.out.println("Факториал натурального числа: " + n + "! = " + answer);
    }

}
