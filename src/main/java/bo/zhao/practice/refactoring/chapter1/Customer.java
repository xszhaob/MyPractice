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
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }


    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental rental : rentals) {
            double thisAmount = 0;

            switch (rental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (rental.getDaysRented() > 2) {
                        thisAmount += (rental.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += rental.getDaysRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (rental.getDaysRented() > 3) {
                        thisAmount += (rental.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }

            frequentRenterPoints++;
            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(String.valueOf(thisAmount)).append("\n");
            totalAmount += thisAmount;
        }

        result.append("Amount owed is ").append(String.valueOf(totalAmount)).append("\n");
        result.append("Your earned ").append(String.valueOf(frequentRenterPoints)).append(" frequent renter points");
        return result.toString();
    }

    public String getName() {
        return name;
    }
}
