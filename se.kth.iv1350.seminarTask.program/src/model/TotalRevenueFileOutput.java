package model;

import model.RevenueObserver;
import util.ExceptionLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Subscriber to the ObservedTotalRevenue that prints the revenue to a log
 */
public class TotalRevenueFileOutput extends TotalIncomeTrackerTemplate {
    private static final String file_name = "Sale_process_income.txt";
    private final PrintWriter printer;
    private ExceptionLogger exceptionLogger;

    /**
     * Constructor that creates an instance of the class
     * @throws IOException if the file writer cannot be created
     */
    public TotalRevenueFileOutput()
            throws IOException {
        printer = new PrintWriter(new FileWriter(file_name,true),true);
    }

    /**
     * Overloaded constructor with an additional exception logger
     * @param logger an instance of the file-writer for the error log
     * @throws IOException if an exception is thrown when creating the FileWriter
     */
    public TotalRevenueFileOutput(ExceptionLogger logger)
            throws IOException {
        printer = new PrintWriter(new FileWriter(file_name,true),true);
        this.exceptionLogger = logger;

    }


    @Override
    protected void doShowTotalIncome(double income) throws Exception {
        String message = createMessageToStore(income);
        printer.printf(message);
    }

    @Override
    protected void handleExceptions(Exception exceptions) {
        exceptionLogger.storeExceptionToFile(exceptions);
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
