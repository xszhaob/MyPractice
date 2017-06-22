package bo.zhao.practice.nio;

import java.nio.ByteBuffer;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/12/13
 * <p/>
 * package_name:bo.zhao.practice.nio
 * <p/>
 * project: MyPractice
 */
public class GetData {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int i = 0;
        /*
        在分配一个ByteBuffer之后，
        可以通过检测它的值来查看缓冲器的分配方式是否将其内容自动置零。
         */
        // buffer.limit();返回缓冲区的限制
        while (i++ < buffer.limit()) {
            // 读取此缓冲区当前位置的字节，然后该位置递增。
            if (buffer.get() != 0) {
                System.out.print("nonzero");
            }
        }
        System.out.println("i = " + i);

        // 重绕此缓冲区。将位置设置为0并丢弃标记。
        // 在一系列通道写入或获取操作之前调用此方法。
        buffer.rewind();

        buffer.asCharBuffer().put("Howdy!");
        char c;
        while ((c = buffer.getChar()) != 0) {
            System.out.print(c + " ");
        }
        System.out.println("");
        buffer.rewind();

        /*
        向ByteBuffer插入基本类型数据的最简单方法是：
        利用asCharBuffer()、asShortBuffer()等获得
        该缓冲器上的视图，然后使用视图的put()方法。
         */
        buffer.asShortBuffer().put((short)47142);
        System.out.println(buffer.getShort());
        buffer.rewind();

        buffer.asIntBuffer().put(99471142);
        System.out.println(buffer.getInt());
        buffer.rewind();

        buffer.asLongBuffer().put(99471142);
        System.out.println(buffer.getLong());
        buffer.rewind();

        buffer.asFloatBuffer().put(99471142);
        System.out.println(buffer.getFloat());
        buffer.rewind();

        buffer.asDoubleBuffer().put(99471142);
        System.out.println(buffer.getDouble());
        buffer.rewind();
    }
}
