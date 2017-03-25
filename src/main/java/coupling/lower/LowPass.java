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
public class LowPass extends Filter {
    double cutoff;
    public LowPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public Waveform process(Waveform input) {
        return input;
    }
}
