package bo.zhao.practice.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * author:xszhaobo
 * <p/>
 * date:2016/12/11
 * <p/>
 * package_name:bo.zhao.practice.nio
 * <p/>
 * project: MyPractice
 */
public class ChannelCopy {
    public static void main(String[] args) throws IOException {
        /*
        打开一个FileChannel以用于读，打开另一个以用于写。
         */
        FileChannel in = new FileInputStream("C:\\Users\\xszhaobo\\Desktop\\data.txt").getChannel();
        FileChannel out = new FileOutputStream("C:\\Users\\xszhaobo\\Desktop\\dataout.txt").getChannel();
        /*
        ByteBuffer被分配了空间，当FileChannel.read()返回-1时，表示已经到达文件的末尾。
        每次read()操作以后，就会将数据输入到缓冲器中，flip()则是准备缓冲器以便它的信息
        可以由write()提取。write()操作之后，信息仍在缓冲器中，接着clear()操作则对所有
        的内部指针重新安排，以便缓冲器在另一个read()操作期间能够做好接收数据的准备。
         */
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (in.read(buffer) > 0) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }

        /*
        特殊方法transferTo()和transferFrom()方法允许我们将一个通道和另一个通道至直接相连。
         */
//        in.transferTo(0,in.size(),out);
    }
}
