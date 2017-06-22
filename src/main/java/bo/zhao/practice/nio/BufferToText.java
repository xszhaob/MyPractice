package bo.zhao.practice.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/12/12
 * <p/>
 * package_name:bo.zhao.practice.nio
 * <p/>
 * project: MyPractice
 */
public class BufferToText {
    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("C:\\Users\\xszhaobo\\Desktop\\data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        fc = new FileInputStream("C:\\Users\\xszhaobo\\Desktop\\data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fc.read(buffer);
        buffer.flip();
        /*
        buffer.asCharBuffer()返回一个CharBuffer对象，直接输出CharBuffer对象则是调用了
        CharBuffer对象的toString()方法。而toString()方法是“返回一个包含缓冲器中所有字符的字符串”。
         */
        System.out.println(buffer.asCharBuffer());

        /*
        我们对缓冲器调用rewind()方法（调用该方法是为了返回到数据开始部分），
        接着使用平台的默认字符集对数据进行decode()，那么作为结果的CharBuffer可以
        很好地输出到控制台。可以使用System.getProperty("file.encoding")发现默认
        字符集，它会产生代表字符集名称的字符串。把该字符串传送给CharSet.forName()用以
        产生CharSet对象，可以用它对字符串进行解码。
         */
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decode using " + encoding + ": " + Charset.forName(encoding).decode(buffer));


        /*
        在读取文件时，使用能够产生可打印的输出的字符集进行encode()。
        UTF-16BE可以把文本写入到文件中，当读取时，我们只需把它转换成CharBuffer，
        即可产生所期望的文本。
         */
        fc = new FileOutputStream("C:\\Users\\xszhaobo\\Desktop\\data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();
        fc = new FileInputStream("C:\\Users\\xszhaobo\\Desktop\\data2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        /*
        如果通过CharBuffer向ByteBuffer写入。我们为ByteBuffer分配了24个字节。
        既然一个字符需要两个字节，那么一个ByteBuffer可以容纳12个字符，
        但是“Some text”只有9个字符，剩余的内容为0的字节仍
        出现在由它的toString()所产生的CharBuffer的表示中。
         */
        fc = new FileOutputStream("C:\\Users\\xszhaobo\\Desktop\\data2.txt").getChannel();
        buffer = ByteBuffer.allocate(24);
        buffer.asCharBuffer().put("Some text");
        fc.write(buffer);
        fc.close();
        fc = new FileInputStream("C:\\Users\\xszhaobo\\Desktop\\data2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println("********" + buffer.asCharBuffer() + "********");

    }
}
