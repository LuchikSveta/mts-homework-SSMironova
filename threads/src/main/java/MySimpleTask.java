import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MySimpleTask implements Callable<List<Integer>> {

    private final Integer[] array;
    private final int start;
    private final int end;

    public MySimpleTask(final Integer[] array, final int start, final int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    public List<Integer> call() {
        List<Integer> result = new ArrayList<>();
        for (int i = start; i < end; i++) {
            int integer = array[i];
            BigInteger bigInteger = BigInteger.valueOf(integer);
            if (bigInteger.isProbablePrime((int) Math.log(integer))) {
               result.add(integer);
            }
        }
        return result;
    }

}
