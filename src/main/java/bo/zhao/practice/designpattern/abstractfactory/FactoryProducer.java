package bo.zhao.practice.designpattern.abstractfactory;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/1/3
 */
public class FactoryProducer {


    public static AbstractFactory getFactory(String choice) {
        if (choice == null) {
            return null;
        }
        if (choice.equalsIgnoreCase(AbstractFactoryConf.FACTORY_SHAPE)) {
            return new ShapeFactory();
        }
        if (choice.equalsIgnoreCase(AbstractFactoryConf.FACTORY_COLOR)) {
            return new ColorFactory();
        }
        return null;
    }
}
