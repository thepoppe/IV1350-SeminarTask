package observer;

import model.RevenueObserver;

import java.util.ArrayList;

public class ObservedTotalRevenue {
    private ArrayList<RevenueObserver> allSubscribers = new ArrayList<>();
    private double totalRevenue = 0;

    public void addObserver(RevenueObserver observer){
        allSubscribers.add(observer);
    }
    public void removeAllObserver() {
            allSubscribers.clear();
    }

    public void performPurchase(double amount){
        totalRevenue += amount;
        callAllObservers(totalRevenue);
    }
    private void callAllObservers(double newRevenue){
        for (RevenueObserver currentSubscriber : allSubscribers) {
            currentSubscriber.updateTotalRevenue(newRevenue);
        }
    }


}
