package bo.zhao.practice.algorithm;

public class InvertedNum {

    public static void main(String[] args) {
        System.out.println(f(123, 0));
    }

    private static int f(int x, int y) {
        return x == 0 ? y : (f(x / 10, y * 10 + x % 10));
    }
}
