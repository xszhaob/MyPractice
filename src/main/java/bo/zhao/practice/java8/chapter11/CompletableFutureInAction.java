package bo.zhao.practice.java8.chapter11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
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
        String product = "MyPhone27";

        findPricesInAction(() -> findPrices(product));

        findPricesInAction(() -> findPricesInParallelStream(product));

        findPricesInAction(() -> findPricesFuture(product));

        findPricesInAction(() -> findDiscountPrices(product));

        findPricesInAction(() -> findDiscountPricesFuture(product));
    }


    private static void findPricesInAction(Supplier<List<String>> supplier) {
        long start = System.currentTimeMillis();
        System.out.println(supplier.get());
        System.out.println("Done in " + (System.currentTimeMillis() - start) + " millis time!");
    }

    private static List<String> findPrices(String product) {
        return createShopList().stream().map(
                shop -> String.format("%s price is %s", shop.getShopName(), shop.getPrice(product))
        ).collect(Collectors.toList());
    }


    private static List<String> findPricesInParallelStream(String product) {
        return createShopList().parallelStream().map(
                shop -> String.format("%s price is %s", shop.getShopName(), shop.getPrice(product))
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

    private static List<String> findDiscountPrices(String product) {
        return createShopList().stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    private static List<String> findDiscountPricesFuture(String product) {
        List<Shop> shops = createShopList();
        ExecutorService executor = Executors.newFixedThreadPool(shops.size(), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        List<CompletableFuture<String>> priceFutures = createShopList().stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}
