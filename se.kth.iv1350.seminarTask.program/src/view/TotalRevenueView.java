package view;

import model.payment.TotalIncomeTrackerTemplate;
import util.ExceptionLogger;


/**
 * Observer class that prints the total income to the user
 */
public class TotalRevenueView extends TotalIncomeTrackerTemplate {
    private ExceptionLogger exceptionLogger;

    /**
     * deafault constructor creates an instance of the class
     */
    public TotalRevenueView(){}

    /**
     * overriden constructor creates an instance of the class with an exception logger
     * @param logger an instance of the file-writer for the error log
     */
    public TotalRevenueView(ExceptionLogger logger){
        this.exceptionLogger = logger;

    }


    /**
     * The specific task to be done when called by the publisher
     * @param income the total income to be displayed to the user
     * @throws Exception if code fails to execute
     */
    @Override
    protected void doShowTotalIncome(double income) throws Exception {
        System.out.printf("The total income is: %.2f", income);
    }

    /**
     * The specific error handling when called by the publisher
     * @param exceptions the registered exception that needs to be handled
     */
    @Override
    protected void handleExceptions(Exception exceptions) {
        System.out.println("An error occurred, try again");
        exceptionLogger.storeExceptionToFile(exceptions);
    }
}
