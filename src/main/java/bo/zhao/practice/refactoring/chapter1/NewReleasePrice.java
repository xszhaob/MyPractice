package bo.zhao.practice.refactoring.chapter1;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/6/26
 */
public class NewReleasePrice extends Price {
    int getPriceCode() {
        return BetterMovie.NEW_RELEASE;
    }

    double getCharge(int daysRented) {
        return daysRented * 3;
    }
}
