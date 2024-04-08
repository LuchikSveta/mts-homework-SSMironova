import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public interface MySimpleMethod {

    static void calculate(final int n, final int threadNum, final int maxValue) throws ExecutionException, InterruptedException {
        Integer[] list = new Integer[n];
        for (int i = 0; i < n; i++) {
            list[i] = 1 + new Random().nextInt(maxValue);
        }
        int chunkSize = n / threadNum;

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        List<Future<List<Integer>>> futures  = new ArrayList<>();
        for(int i = 0; i < threadNum; i++){
            int startIndex = i * chunkSize;
            int endIndex = (i == threadNum - 1) ? n : startIndex + chunkSize;
            futures.add(executor.submit(new MySimpleTask(list, startIndex, endIndex)));
        }

        List<Integer> answer = new ArrayList<>();
        for (Future<List<Integer>> future : futures) {
            answer.addAll(future.get());
        }
        executor.shutdown();
        if (answer.size() == 0) {
            System.out.println("В списке " + Arrays.toString(list) + " нет простых чисел");
        } else {
            System.out.println("Простые числа: " + answer);
        }
    }

}
