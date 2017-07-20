package bo.zhao.practice.refactoring.chapter06;

/**
 * Created by zhaobo on 2017/7/20.
 */
public class IntroduceExplainingVariable {

    public void doSomething(String platform, int resize) {
        if (platform.toUpperCase().contains("MAC") &&
                platform.toUpperCase().contains("IE") &&
                wasInitialized() && resize > 0) {
            System.out.println("do something...");
        }
    }


    private boolean wasInitialized() {
        return true;
    }


    private int quantity = 10;
    private int itemPrice = 2;

    double price() {
        return quantity * itemPrice - Math.max(0, quantity - 500) * itemPrice * 0.05 + Math.min(quantity * itemPrice * 0.01, 100.0);
    }


}
