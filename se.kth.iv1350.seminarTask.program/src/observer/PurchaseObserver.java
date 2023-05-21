package observer;

import model.purchase.RevenueObserver;

import java.util.ArrayList;

public class PurchaseObserver  {
    private ArrayList<RevenueObserver> allObservers= new ArrayList<>();
    private double totalRevenue = 0;

    public void addObserver(RevenueObserver observer){
        allObservers.add(observer);
    }
    public void removeAllObserver() {
            allObservers.clear();
    }

    public void performPurchase(double amount){
        totalRevenue += amount;
        callAllObservers(totalRevenue);
    }
    private void callAllObservers(double newRevenue){
        for (RevenueObserver observer : allObservers) {
            observer.updateTotalRevenue(newRevenue);
        }
    }


}
