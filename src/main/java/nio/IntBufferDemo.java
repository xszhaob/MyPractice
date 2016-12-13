package nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/12/13
 * <p/>
 * package_name:nio
 * <p/>
 * project: MyPractice
 */
public class IntBufferDemo {
    public static void main(String[] args) {
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
