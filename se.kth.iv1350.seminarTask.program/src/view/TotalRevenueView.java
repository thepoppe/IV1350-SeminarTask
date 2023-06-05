package view;

import model.RevenueObserver;
import model.TotalIncomeTrackerTemplate;
import util.ExceptionLogger;


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


    @Override
    protected void doShowTotalIncome(double income) throws Exception {
        System.out.printf("The total income is: %.2f", income);
    }
    @Override
    protected void handleExceptions(Exception exceptions) {
        System.out.println("An error occurred, try again");
        exceptionLogger.storeExceptionToFile(exceptions);
    }
}
