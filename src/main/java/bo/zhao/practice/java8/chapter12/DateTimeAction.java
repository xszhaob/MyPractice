package bo.zhao.practice.java8.chapter12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

/**
 * Created by zhaobo on 2018/7/22.
 */
public class DateTimeAction {

    public static void main(String[] args) {
//        localDateInAction();
//        localTimeInAction();
//        localDateTimeInAction();
//        instantInAction();
//        durationInAction();
//        periodInAction();
        temporalAdjustersInAction();
}


    private static void localDateInAction() {
        // 构造函数
        LocalDate localDate = LocalDate.of(2018, 7, 22);
        System.out.println(localDate);

        // 当前时间
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.getYear());
        System.out.println(now.getMonth());
        System.out.println(now.lengthOfMonth());
        // 闰年
        System.out.println(now.isLeapYear());

        System.out.println(now.get(ChronoField.YEAR));

        LocalDate date1 = LocalDate.parse("2018-07-31");
        System.out.println(date1);
        LocalDate date2 = date1.withYear(2011);
        System.out.println(date2);
    }


    private static void localTimeInAction() {
        LocalTime now = LocalTime.now();
        System.out.println(now);
        System.out.println(now.get(ChronoField.MILLI_OF_SECOND));
    }


    private static void localDateTimeInAction() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2018-07-22 17:18:14", formatter);
        System.out.println(dateTime);

        dateTime = LocalDateTime.of(2018, 7, 22, 0, 22);
        System.out.println(dateTime);
        System.out.println(dateTime.toLocalDate());
        System.out.println(dateTime.toLocalTime());

        LocalDate today = LocalDate.now();
        LocalTime nowTime = LocalTime.now();
        dateTime = LocalDateTime.of(today, nowTime);
        System.out.println(dateTime);
    }

    private static void instantInAction() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant instant3 = Instant.ofEpochSecond(3, 0);
        Instant instant4 = Instant.ofEpochSecond(4, -1_000_000_000);
        System.out.println(instant1.equals(instant2));
        System.out.println(instant1.equals(instant3));
        System.out.println(instant1.equals(instant4));
        System.out.println(instant1 == instant2);
    }

    private static void durationInAction() {
        LocalTime now = LocalTime.now();
        LocalTime localTime = LocalTime.now().plus(400, ChronoUnit.SECONDS);
        Duration between = Duration.between(now, localTime);
        System.out.println(between.getSeconds());
        Duration duration = between.plusDays(1);
        System.out.println(duration.getSeconds());
    }


    private static void periodInAction() {
        LocalDate localDate1 = LocalDate.of(2018, 7, 22);
        LocalDate localDate2 = LocalDate.of(2018, 7, 23);
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period.getDays());
    }


    private static void temporalAdjustersInAction() {
        LocalDate date = LocalDate.of(2018, 7, 22);
        LocalDate with = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(with);

        TemporalAdjuster temporalAdjuster = TemporalAdjusters.dayOfWeekInMonth(7, DayOfWeek.MONDAY);
        System.out.println(temporalAdjuster.adjustInto(date));
        TemporalAdjuster adjuster = TemporalAdjusters.firstDayOfMonth();
        System.out.println(adjuster.adjustInto(date));
        TemporalAdjuster adjuster1 = TemporalAdjusters.lastDayOfMonth();
        System.out.println(adjuster1.adjustInto(date));
        System.out.println(TemporalAdjusters.lastDayOfYear().adjustInto(date));
        System.out.println(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY).adjustInto(date));

        date = LocalDate.of(2018, 7, 23);
        System.out.println(NextWorkingDay.getAdjuster().adjustInto(date));
    }
}
