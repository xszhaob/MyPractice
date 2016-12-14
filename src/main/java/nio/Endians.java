package nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/12/14
 * <p/>
 * package_name:nio
 * <p/>
 * project: MyPractice
 */
public class Endians {
    public static void main(String[] args) {
        /*
        ByteBuffer有足够的空间，以存储作为外部缓冲器的charArray中
        的所有字节，因此可以调用array()方法显示视图底层的字节。
        array()方法是“可选的”，并且我们只能对由数组支持的缓冲器调用
        此方法；否则，将会抛出UnsupportedOperationException。
        通过CharBuffer视图可以将charArray插入到ByteBuffer中。在底层
        的字节被显示时，我们会发现默认次序是高位优先；而低位优先次序则与之相反。
         */
        ByteBuffer buffer = ByteBuffer.wrap(new byte[12]);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
        buffer.rewind();

        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
        buffer.rewind();

        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(buffer.array()));
    }
}
