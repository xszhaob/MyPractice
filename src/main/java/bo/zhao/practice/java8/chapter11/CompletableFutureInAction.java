package bo.zhao.practice.java8.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/7/12
 */
public class CompletableFutureInAction {

    public static void main(String[] args) {
        findPricesInAction();

        findPricesInParallelStreamInAction();

        findPricesFutureInAction();
    }


    private static void findPrice() {
        Shop shop = new Shop("MyShop");

        long start = System.currentTimeMillis();
        CompletableFuture<Double> futurePrice = shop.getPriceAsync("my favorite product");
        System.out.println("Invocation cost " + (System.currentTimeMillis() - start) + " millis times");

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Double price = futurePrice.get(1000L, TimeUnit.SECONDS);
            System.out.println("price is " + price);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        long retrievalTime = System.currentTimeMillis() - start;
        System.out.println("Price returned after " + retrievalTime + " millis time");
    }

    private static void findPricesInAction() {
        long start = System.currentTimeMillis();
        System.out.println(findPrices("MyPhone27"));
        System.out.println("Done in " + (System.currentTimeMillis() - start) + " millis time!");
    }

    private static List<String> findPrices(String product) {
        return createShopList().stream().map(
                shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product))
        ).collect(Collectors.toList());
    }

    private static void findPricesInParallelStreamInAction() {
        long start = System.currentTimeMillis();
        System.out.println(findPricesInParallelStream("MyPhone27"));
        System.out.println("Done in " + (System.currentTimeMillis() - start) + " millis time!");
    }

    private static List<String> findPricesInParallelStream(String product) {
        return createShopList().parallelStream().map(
                shop -> String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product))
        ).collect(Collectors.toList());
    }

    private static List<Shop> createShopList() {
        return Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop1"),
                new Shop("MyFavoriteShop2"),
                new Shop("MyFavoriteShop3"),
                new Shop("MyFavoriteShop4"),
                new Shop("MyFavoriteShop5"),
                new Shop("MyFavoriteShop6"),
                new Shop("MyFavoriteShop7"),
                new Shop("BuyItAll"));
    }

    private static void findPricesFutureInAction() {
        long start = System.currentTimeMillis();
        System.out.println(findPricesFuture("MyPhone27"));
        System.out.println("Done in " + (System.currentTimeMillis() - start) + " millis time!");
    }

    private static List<String> findPricesFuture(String product) {
        List<Shop> shops = createShopList();
        ExecutorService executor = Executors.newFixedThreadPool(shops.size(), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        List<CompletableFuture<String>> future = shops.stream().map((
                shop -> CompletableFuture.supplyAsync(
                        () -> shop.getShopName() + " price is " + shop.getPrice(product),
                        executor)
        )).collect(Collectors.toList());

        return future.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}
