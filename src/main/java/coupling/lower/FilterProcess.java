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
public class FilterProcess {
    public static void main(String[] args) {
        Waveform waveform = new Waveform();
        Apply.process(new FilterAdapter(new LowPass(1.0)), waveform);
        Apply.process(new FilterAdapter(new HighPass(2.0)), waveform);
        Apply.process(new FilterAdapter(new BandPass(3.0, 4.0)), waveform);
    }
}
