package coupling.lower;

/**
 * author:xszhaobo
 * <p/>
 * date:2017/3/25
 * <p/>
 * package_name:coupling
 * <p/>
 * project: MyPractice
 */
public class Apply {
    public static void process(Processor p, Object s) {
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }

    public static String s = "Disagreement with beliefs is by definition incorrect";

    public static void main(String[] args) {
        process(new UpCase(), s);
        process(new DownCase(), s);
        process(new Splitter(), s);
    }
}



