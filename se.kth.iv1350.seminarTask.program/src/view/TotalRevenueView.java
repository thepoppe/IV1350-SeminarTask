package view;

import model.purchase.RevenueObserver;

public class TotalRevenueView implements RevenueObserver {
    @Override
    public void updateTotalRevenue(double newRevenue) {
        String line = "--------------------------------------------";
        System.out.printf("%s\nTotal income: %.2f kr\n%s\n\n", line,newRevenue, line);
    }
}
