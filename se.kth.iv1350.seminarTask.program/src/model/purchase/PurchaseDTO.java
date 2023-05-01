package model.purchase;

import java.util.ArrayList;

public class PurchaseDTO {

    private ArrayList<SoldItem> registeredItems;
    private double  totalVAT;
    private double runningTotal;


    public PurchaseDTO(SaleLog saleLog){
        this.registeredItems = saleLog.getListOfSoldItems();
        this.runningTotal = saleLog.getRunningTotal().getAmount();
        this.totalVAT = saleLog.getTotalVAT().getAmount();
    }

    public ArrayList<SoldItem> getRegisteredItems() {
        return registeredItems;
    }

    public double getRunningTotal() {
        return runningTotal;
    }

    public double getTotalVAT() {
        return totalVAT;
    }
}
