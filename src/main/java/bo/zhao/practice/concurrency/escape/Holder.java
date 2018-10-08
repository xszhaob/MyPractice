package bo.zhao.practice.concurrency.escape;

public class Holder {

    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("this statement is false");
        }
    }
}
