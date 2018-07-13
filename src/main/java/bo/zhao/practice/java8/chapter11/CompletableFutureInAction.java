package bo.zhao.practice.java8.chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
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

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(createShopList().size(), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void main(String[] args) {
        String product = "MyPhone27";

//        findPricesInAction(() -> findPrices(product));

//        findPricesInAction(() -> findPricesInParallelStream(product));

//        findPricesInAction(() -> findPricesFuture(product));

//        findPricesInAction(() -> findDiscountPrices(product));

//        findPricesInAction(() -> findDiscountPricesFuture(product));

//        findPricesInAction(() -> findPricesInSerialFuture(product));

        findPricesInActionTemplate(CompletableFutureInAction::findPricesStream, product);
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    private static <T> void findPricesInVoidAction(Consumer<T> consumer, T t) {
        findPricesInActionTemplate(consumer, t);
    }

    private static void findPricesInAction(Supplier<List<String>> supplier) {
        findPricesInActionTemplate(System.out::println, supplier.get());
    }

    private static <T> void findPricesInActionTemplate(Consumer<T> consumer, T t) {
        long start = System.currentTimeMillis();
        consumer.accept(t);
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

        List<CompletableFuture<String>> future = shops.stream().map((
                shop -> CompletableFuture.supplyAsync(
                        () -> shop.getShopName() + " price is " + shop.getPrice(product),
                        EXECUTOR)
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
        List<CompletableFuture<String>> priceFutures = createShopList().stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), EXECUTOR))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), EXECUTOR)))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private static List<String> findPricesInSerialFuture(String product) {
        List<Shop> shops = createShopList();

        List<String> prices = new ArrayList<>(6);
        CompletableFuture<String> future0 = CompletableFuture.supplyAsync(() -> shops.get(0).getPrice(product), EXECUTOR);
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> shops.get(1).getPrice(product), EXECUTOR);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> shops.get(2).getPrice(product), EXECUTOR);
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> shops.get(3).getPrice(product), EXECUTOR);
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> shops.get(4).getPrice(product), EXECUTOR);
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> shops.get(5).getPrice(product), EXECUTOR);
        CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> shops.get(6).getPrice(product), EXECUTOR);

        try {
            prices.add(future0.get());
            prices.add(future1.get());
            prices.add(future2.get());
            prices.add(future3.get());
            prices.add(future4.get());
            prices.add(future5.get());
            prices.add(future6.get());
            return prices;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void findPricesStream(String product) {
        List<CompletableFuture<String>> futureList = createShopList().stream()
                .map(shop ->
                        CompletableFuture.supplyAsync(() -> shop.getPriceInRandomDelay(product), EXECUTOR))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose((quote) ->
                        CompletableFuture.supplyAsync(() ->
                                Discount.applyDiscount(quote), EXECUTOR)))
                .collect(Collectors.toList());

        long start = System.currentTimeMillis();
        CompletableFuture[] futures = futureList.stream()
                .map(future -> future.thenAccept((price) ->
                        System.out.println(
                                price + "(done in " + (System.currentTimeMillis() - start) + ")")))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
    }
}
