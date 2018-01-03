package bo.zhao.practice.designpattern.abstractfactory;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/3
 */
public class Square implements Shape{

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
