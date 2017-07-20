package bo.zhao.practice.refactoring.chapter06;

/**
 * Created by zhaobo on 2017/7/20.
 * 分解临时变量
 */
public class SplitTmeporaryVariable {
    private double primaryForce;
    private double mass;
    private double secondaryForce;
    private int delay;

    private double getDistanceTravelled(int time) {
        double result;
        double acc = primaryForce / mass;
        int primaryTime = Math.min(time,delay);
        result = 0.5 * acc * primaryTime * primaryTime;
        int secondaryTime = time - delay;
        if (secondaryTime > 0) {
            double primaryVel = acc * delay;
            acc = (primaryForce + secondaryForce) / mass;
            result += primaryVel * secondaryTime + 0.5 * acc * secondaryTime * secondaryTime;
        }
        return result;
    }
}
