package bo.zhao.practice.refactoring.chapter06;

/**
 * Created by zhaobo on 2017/7/20.
 */
public class IntroduceExplainingVariableV2 {


    public void doSomething(String platform, int resize) {
        final boolean isMacOs = platform.toUpperCase().contains("MAC");
        final boolean isIEBrowser = platform.toUpperCase().contains("IE");
        final boolean wasResized = resize > 0;
        if (isMacOs && isIEBrowser && wasInitialized() && wasResized) {
            System.out.println("do something...");
        }
    }


    private boolean wasInitialized() {
        return true;
    }



    private int quantity = 10;
    private int itemPrice = 2;

    double price() {
        return basePrice() - quantityDiscount() + shipping();
    }

    /**
     * 底价
     */
    private int basePrice() {
        return quantity * itemPrice;
    }

    /**
     * 折扣
     */
    private double quantityDiscount() {
        return Math.max(0, quantity - 500) * itemPrice * 0.05;
    }

    /**
     * 运费
     */
    private double shipping() {
        return Math.min(quantity * itemPrice * 0.01, 100.0);
    }
}
