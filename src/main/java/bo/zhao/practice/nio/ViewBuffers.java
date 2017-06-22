package bo.zhao.practice.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/12/13
 * <p/>
 * package_name:bo.zhao.practice.nio
 * <p/>
 * project: MyPractice
 */
public class ViewBuffers {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.wrap(new byte[]{0,0,0,0,0,0,0,'a'});
        buffer.rewind();
        System.out.print("Byte Buffer ");
        while (buffer.hasRemaining()) {
            System.out.printf(buffer.position() + "->" + buffer.get() + ",");
        }
        System.out.println("");

        CharBuffer charBuffer = ((ByteBuffer) buffer.rewind()).asCharBuffer();
        System.out.printf("Char Buffer ");
        while (charBuffer.hasRemaining()) {
            System.out.printf(charBuffer.position() + "->" + charBuffer.get() + ",");
        }
    }
}
