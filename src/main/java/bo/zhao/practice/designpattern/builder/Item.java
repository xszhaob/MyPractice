package bo.zhao.practice.designpattern.builder;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/4
 */
public interface Item {

    String name();

    Packing packing();

    float price();
}
