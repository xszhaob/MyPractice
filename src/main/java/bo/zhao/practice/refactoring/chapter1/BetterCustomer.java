package bo.zhao.practice.refactoring.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 17/6/22
 */
public class BetterCustomer {
    private String name;
    private List<BetterRental> rentals = new ArrayList<BetterRental>();

    public BetterCustomer(String name) {
        this.name = name;
    }

    public void addRental(BetterRental rental) {
        rentals.add(rental);
    }


    public String statement() {
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        for (BetterRental rental : rentals) {
            result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(String.valueOf(rental.getCharge())).append("\n");
        }
        result.append("Amount owed is ").append(String.valueOf(getTotalCharge())).append("\n");
        result.append("Your earned ").append(String.valueOf(getFrequentRenterPoints())).append(" frequent renter points");
        return result.toString();
    }


    private double getTotalCharge() {
        double result = 0;
        for (BetterRental rental : rentals) {
            result += rental.getCharge();
        }
        return result;
    }

    private int getFrequentRenterPoints() {
        int result = 0;
        for (BetterRental rental : rentals) {
            result += rental.getFrequentRenterPoints();
        }
        return result;
    }

    public String getName() {
        return name;
    }
}
