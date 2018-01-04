package bo.zhao.practice.designpattern.builder;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/4
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
