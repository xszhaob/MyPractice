package bo.zhao.practice.refactoring.chapter06;

/**
 * Created by zhaobo on 2017/7/20.
 */
public class ReplaceTempWithQueryDemo {
    private int quantity = 10;
    private int itemPrice = 2;

    public double getPrice() {
        int basePrice = quantity * itemPrice;
        double discountFactor;
        if (basePrice > 1000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }
}
