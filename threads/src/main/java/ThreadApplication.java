import service.CounterMethod;
import service.FactorialMethod;
import service.SimpleMethod;

import java.util.concurrent.ExecutionException;

public class ThreadApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Задание №2: Потокобезопасный счетчик");
        CounterMethod.calculate(8, 3);
        System.out.println("\nЗадание №4: Параллельное вычисление факториала");
        FactorialMethod.calculate(4, 3);
        FactorialMethod.calculate(5, 5);
        FactorialMethod.calculate(8, 4);
        System.out.println("\nЗадание №5: Параллельная проверка чисел на простоту");
        SimpleMethod.calculate(5, 3, 20);
        SimpleMethod.calculate(20, 7, 200);
    }

}
