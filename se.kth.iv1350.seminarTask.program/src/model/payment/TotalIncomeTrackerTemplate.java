package model.payment;

/**
 * Abstract class for task 1 in higher grade tasks for the course iv1350.
 * Implementation f the template method to show the total income.
 */
public abstract class TotalIncomeTrackerTemplate implements RevenueObserver{


    /**
     * registers a new income and displays it
     * @param newIncome the registered income
     * @throws PaymentException is thrown if the observer TotalRevenueFileOutput cant execute
     */
    @Override
    public void updateTotalRevenue(double newIncome) throws PaymentException {
        showTotalIncome(newIncome);

    }


    /**
     * private method that tries to display yhe new total revenue
     * @param updatedIncome the amount to be displayed by the observers
     * @throws PaymentException is thrown if the observer TotalRevenueFileOutput cant execute
     */
    private void showTotalIncome(double updatedIncome) throws PaymentException {
        try {
            doShowTotalIncome(updatedIncome);
        }
        catch (Exception allExceptions){
            handleExceptions(allExceptions);
        }
    }


    /**
     * shows the income
     * @param incomeToBeShown the income registered from the purchase
     * @throws Exception if income cant be displayed.
     */
    protected abstract void doShowTotalIncome(double incomeToBeShown)
            throws Exception;

    /**
     * method that handles potential exceptions
     * @param exceptions the collected exception to handle
     * @throws PaymentException is thrown if the observer TotalRevenueFileOutput cant execute
     */
    protected abstract void handleExceptions(Exception exceptions) throws PaymentException;


}
