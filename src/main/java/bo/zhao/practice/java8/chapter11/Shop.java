package bo.zhao.practice.java8.chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/12
 */
public class Shop {

    private static Random random = new Random();

    private final String shopName;


    public CompletableFuture<Double> getPriceAsync(String product) {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double priceAsync = calculatePrice(product);
                future.complete(priceAsync);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();

        return future;
    }

    public CompletableFuture<Double> getPriceAsyncBetter(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public String getPrice(String product) {
        return formatByDiscount(calculatePriceByFixed(product));
    }

    public String getPriceInRandomDelay(String product) {
        return formatByDiscount(calculatePriceByRandom(product));
    }

    private String formatByDiscount(double price) {
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", getShopName(), price, code);
    }

    private double calculatePriceByFixed(String product) {
        delay();
        return calculatePrice(product);
    }

    private double calculatePriceByRandom(String product) {
        randomDelay();
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void randomDelay() {
        try {
            int delay = 500 + random.nextInt(2000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
