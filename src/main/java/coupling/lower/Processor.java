package coupling.lower;

/**
 * author:xszhaobo
 * <p/>
 * date:2017/3/25
 * <p/>
 * package_name:coupling.lower
 * <p/>
 * project: MyPractice
 */
public interface Processor {

    String name();

    Object process(Object input);
}
