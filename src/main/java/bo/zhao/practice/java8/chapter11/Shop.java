package bo.zhao.practice.java8.chapter11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

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

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
