import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Задание №2: Потокобезопасный счетчик");
        MyCounter.calculate(8, 3);
        System.out.println("Задание №4: Параллельное вычисление факториала");
        MyFactorial.calculate(4, 3);
        MyFactorial.calculate(5, 5);
        MyFactorial.calculate(8, 4);
        System.out.println("Задание №5: Параллельная проверка чисел на простоту");
        MySimpleMethod.calculate(5, 3, 20);
        MySimpleMethod.calculate(20, 7, 200);
    }

}
