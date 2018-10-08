package bo.zhao.practice.concurrency.escape;

public class TestEscape {
    public static void main(String[] args) {
        Holder holder = new Holder(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                holder.assertSanity();
            }
        }).start();
    }
}
