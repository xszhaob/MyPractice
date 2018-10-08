package bo.zhao.practice.jp.thread;

import java.util.concurrent.*;

public class FutureAction {

    public static void main(String[] args) {
        int count = 5;
        ExecutorService executor = Executors.newFixedThreadPool(count);
        CompletionService<Integer> service = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < count; i++) {
            final int n = i;
            service.submit(() -> {
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
                System.out.println("任务" + n + "完成");
                return n;
            });
        }

        for (int i = 0; i < count; i++) {
            try {
                Integer result = service.take().get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
