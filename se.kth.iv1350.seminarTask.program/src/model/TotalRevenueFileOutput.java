package model;

import model.RevenueObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Subscriber to the PurchaseObserver that prints the revenue to a log
 */
public class TotalRevenueFileOutput implements RevenueObserver {
    private static final String file_name = "Sale_process_income.txt";
    private final PrintWriter printer;

    /**
     * Constructor that creates an instance of the class
     * @throws IOException if the file writer cannot be created
     */
    public TotalRevenueFileOutput()
            throws IOException {
        printer = new PrintWriter(new FileWriter(file_name,true),true);
    }

    /**
     * The method that is tracked from the PurchaseObserver prints the revenue to a file.
     * @param newIncome the new income from a sale that is to be stored
     */
    @Override
    public void updateTotalRevenue(double newIncome) {
        String message = createMessageToStore(newIncome);
        printer.printf(message);
    }

    /**
     * private method that generates the string to save to the log
     * @param newIncome the new income from a sale that is to be stored
     * @return a string with the message to print
     */
    private String createMessageToStore(double newIncome) {
        return "Date of file-writing : " +
                java.time.LocalDate.now() +
                "\nCurrent total revenue: " +
                String.format("%.3f", newIncome) +
                " kr\n\n";
    }
}
