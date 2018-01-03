package bo.zhao.practice.designpattern.abstractfactory;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/3
 */
public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shape) {
        if (shape == null) {
            return null;
        }
        if (shape.equalsIgnoreCase(AbstractFactoryConf.SHAPE_CIRCLE)) {
            return new Circle();
        }
        if (shape.equalsIgnoreCase(AbstractFactoryConf.SHAPE_RECTANGLE)) {
            return new Rectangle();
        }
        if (shape.equalsIgnoreCase(AbstractFactoryConf.SHAPE_SQUARE)) {
            return new Square();
        }
        return null;
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
