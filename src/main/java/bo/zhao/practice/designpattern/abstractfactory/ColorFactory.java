package bo.zhao.practice.designpattern.abstractfactory;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/3
 */
public class ColorFactory extends AbstractFactory {



    @Override
    public Shape getShape(String shape) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase(AbstractFactoryConf.COLOR_RED)) {
            return new Red();
        }
        if (color.equalsIgnoreCase(AbstractFactoryConf.COLOR_GREEN)) {
            return new Green();
        }
        if (color.equalsIgnoreCase(AbstractFactoryConf.COLOR_BLUE)) {
            return new Blue();
        }
        return null;
    }
}
