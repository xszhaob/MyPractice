package bo.zhao.practice.refactoring.chapter1;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/6/22
 */
public class Rental {
    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }
}
