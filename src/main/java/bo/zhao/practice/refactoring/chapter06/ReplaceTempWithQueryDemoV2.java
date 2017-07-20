package bo.zhao.practice.refactoring.chapter06;

/**
 * Created by zhaobo on 2017/7/20.
 */
public class ReplaceTempWithQueryDemoV2 {
    private int quantity = 10;
    private int itemPrice = 2;

    public double getPrice() {
        return getBasePrice() * getDiscountFactor();
    }

    private int getBasePrice() {
        return quantity * itemPrice;
    }

    private double getDiscountFactor() {
        return getBasePrice() > 1000 ? 0.95 : 0.98;
    }
}
