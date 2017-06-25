package bo.zhao.practice.refactoring.chapter1;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/6/22
 */
public class BetterRental {
    private BetterMovie movie;
    private int daysRented;

    public BetterRental(BetterMovie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double getCharge() {
        return getMovie().getCharge(getDaysRented());
    }

    public int getFrequentRenterPoints() {
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1) {
            return 2;
        } else {
            return 1;
        }
    }

    public BetterMovie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
