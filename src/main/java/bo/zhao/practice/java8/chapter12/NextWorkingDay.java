package bo.zhao.practice.java8.chapter12;

import java.time.DayOfWeek;
import java.time.temporal.*;

/**
 * Created by zhaobo on 2018/7/22.
 */
public class NextWorkingDay implements TemporalAdjuster {

    private final static TemporalAdjuster ADJUSTER;

    static {
        ADJUSTER = TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayToAdd = 1;
        if (dow == DayOfWeek.FRIDAY) {
            dayToAdd = 3;
        } else if (dow == DayOfWeek.SATURDAY) {
            dayToAdd = 2;
        }
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }

    public static TemporalAdjuster getAdjuster() {
        return ADJUSTER;
    }
}
