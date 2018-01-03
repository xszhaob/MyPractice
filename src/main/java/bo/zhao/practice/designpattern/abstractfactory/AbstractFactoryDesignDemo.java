package bo.zhao.practice.designpattern.abstractfactory;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/3
 */
public class AbstractFactoryDesignDemo {

    public static void main(String[] args) {
        AbstractFactory shapeFactory = FactoryProducer.getFactory(AbstractFactoryConf.FACTORY_SHAPE);
        Shape shape1 = shapeFactory.getShape(AbstractFactoryConf.SHAPE_CIRCLE);
        shape1.draw();

        Shape shape2 = shapeFactory.getShape(AbstractFactoryConf.SHAPE_RECTANGLE);
        shape2.draw();

        AbstractFactory colorFactory = FactoryProducer.getFactory(AbstractFactoryConf.FACTORY_COLOR);
        Color color = colorFactory.getColor(AbstractFactoryConf.COLOR_BLUE);
        color.fill();
    }
}
