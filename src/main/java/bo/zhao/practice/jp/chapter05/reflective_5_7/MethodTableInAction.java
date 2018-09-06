package bo.zhao.practice.jp.chapter05.reflective_5_7;

import java.lang.reflect.Method;

public class MethodTableInAction {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MethodTableInAction.class.getMethod("square", double.class);

        printTable(1, 10, 10, method);
    }

    public static double square(double x) {
        return x * x;
    }


    public static void printTable(double from, double to, int n, Method method) {
        System.out.println(method);

        double dc = (to - from) / (n - 1);

        for (double x = from; x <= to; x += dc) {
            try {
                Double y = (Double) method.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
