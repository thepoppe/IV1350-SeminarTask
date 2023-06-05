package model;

/**
 * Abstract class for task 1 in higher grade tasks for the course iv1350.
 * Implementation f the template method to show the total income.
 */
public abstract class TotalIncomeTrackerTemplate implements RevenueObserver{


    /**
     * registers a new income and displays it
     * @param newIncome the registered income
     */
    @Override
    public void updateTotalRevenue(double newIncome){
        showTotalIncome(newIncome);

    }


    private void showTotalIncome(double updatedIncome){
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
     * @param exceptions potential exceptions to be thrown
     */
    protected abstract void handleExceptions(Exception exceptions);


}
