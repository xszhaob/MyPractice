package bo.zhao.practice.refactoring.chapter1;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/6/26
 */
public class ChildrenPrice extends Price {
    int getPriceCode() {
        return BetterMovie.CHILDREN;
    }

    double getCharge(int daysRented) {
        double result = 0;
        result += 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }
}
