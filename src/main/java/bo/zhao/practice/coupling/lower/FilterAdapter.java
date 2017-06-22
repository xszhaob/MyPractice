package bo.zhao.practice.coupling.lower;

/**
 * author:xszhaobo
 * <p/>
 * date:2017/3/25
 * <p/>
 * package_name:bo.zhao.practice.coupling.lower
 * <p/>
 * project: MyPractice
 */
public class FilterAdapter implements Processor {
    Filter filter;
    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }
    public String name() {
        return filter.name();
    }

    public Object process(Object input) {
        return filter.process((Waveform) input);
    }
}
