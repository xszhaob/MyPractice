package bo.zhao.practice.java8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/6
 */
public class LambdaUtils {

    public static void main(String[] args) throws IOException {

//        processFile((BufferedReader reader) ->
//                reader.readLine() + reader.readLine()
//        );


        List<Integer> integers = new ArrayList<>(5);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        forEach(integers, System.out::println);

        System.out.println(getTotal(integers, (int n1, int n2) -> n1 * n2));
    }

    public static String processFile(BufferedReadProcessor processor) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(reader);
        }
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static <T extends Number> int getTotal(List<T> list, IntBinaryOperator operator) {
        int total = 0;
        for (T t : list) {
            total += operator.applyAsInt(t.intValue(), t.intValue());
        }
        return total;
    }

}
