package bo.zhao.practice.algorithm.chapter03._3_4;

public class StringHashTest {

    public static void main(String[] args) {
        String a1 = "AaAaAa";
        String a2 = "BBBBBB";
        String a3 = "BBAaBB";
        String a4 = "AaBBBB";
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());
        System.out.println(a3.hashCode());
        System.out.println(a4.hashCode());
    }
}
