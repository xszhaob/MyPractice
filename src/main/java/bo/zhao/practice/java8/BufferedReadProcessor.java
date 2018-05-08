package bo.zhao.practice.java8;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 文件描述：函数式接口
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/6
 */
@FunctionalInterface
public interface BufferedReadProcessor {

    String process(BufferedReader br) throws IOException;
}
