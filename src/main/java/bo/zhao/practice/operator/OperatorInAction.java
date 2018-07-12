package bo.zhao.practice.operator;

/**
 * Created by zhaobo on 2018/7/1.
 */
public class OperatorInAction {
    public static void main(String[] args) {
        // << 向右位移3位，相当于乘以2的3次方
        System.out.println(8 << 3);
        // >> 向左位移3位，相当于除以2的2次方
        System.out.println(8 >> 2);
        // 位与运算符。两个数都转为二进制，然后从高位开始比较，如果两个数都为1则为1，否则为0
        System.out.println(8 & 2);
        // 位或运算符。两个数都转为二进制，然后从高位开始比较，两个数只要有一个为1则为1，否则就为0
        System.out.println(8 | 1);

    }
}
