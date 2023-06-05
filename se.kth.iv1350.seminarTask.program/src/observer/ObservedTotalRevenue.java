package observer;

import model.payment.PaymentException;
import model.payment.RevenueObserver;
import java.util.ArrayList;

/**
 * Stores information about the total Revenue and calls the subscribers to display it
 */
public class ObservedTotalRevenue {
    private ArrayList<RevenueObserver> allSubscribers = new ArrayList<>();
    private double totalRevenue = 0;

    /**
     * adds the observer to the list of subscribers
     * @param observer the observer to add
     */
    public void addObserver(RevenueObserver observer){
        allSubscribers.add(observer);
    }

    /**
     * removes all observers from the list of subscribers
     */
    public void removeAllObserver() {
            allSubscribers.clear();
    }

    /**
     * performPurchase updates the total revenue and call the observers to display this updated amount.
     * @param amount the amount to be added
     * @throws PaymentException is thrown if the observer TotalRevenueFileOutput cant execute
     */
    public void performPurchase(double amount) throws PaymentException {
        totalRevenue += amount;
        callAllObservers(totalRevenue);
    }

    /**
     * The call to the subscribers in the list
     * @param newRevenue the amount to be added
     * @throws PaymentException is thrown if the observer TotalRevenueFileOutput cant execute
     */
    private void callAllObservers(double newRevenue) throws PaymentException {
        for (RevenueObserver currentSubscriber : allSubscribers) {
            currentSubscriber.updateTotalRevenue(newRevenue);
        }
    }


}
