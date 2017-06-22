package bo.zhao.practice.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/12/13
 * <p/>
 * package_name:bo.zhao.practice.nio
 * <p/>
 * project: MyPractice
 */
public class IntBufferDemo {
    public static void main(String[] args) {
        /*
        视图缓冲器（view buffer） 可以让我们通过某个特定的基本数据类型的视窗查看其底层的ByteBuffer。
        ByteBuffer依然是实际存储数据的地方，“支持”着前面的视图，
        因此，对视图的任何修改都会映射称为对ByteBuffer中数据的修改。
        视图还允许我们从ByteBuffer一次一个地（与ByteBuffer所支持的方式相同）或成批地（放入数组中）读取
        基本类型值。
         */
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        IntBuffer intBuffer = buffer.asIntBuffer();

        intBuffer.put(new int[]{11,42,47,99,143,811,1016});

        System.out.println(intBuffer.get(3));
        intBuffer.put(3,1811);
        intBuffer.flip();

        while (intBuffer.hasRemaining()) {
            System.out.print(intBuffer.get() + " ");
        }
    }
}
