package coupling;

/**
 * author:xszhaobo
 * <p/>
 * date:2017/3/25
 * <p/>
 * package_name:coupling
 * <p/>
 * project: MyPractice
 */
public class Waveform {
    private static long counter;
    private final long id = counter;
    public String toString() {
        return "Waveform id " + id;
    }
}
