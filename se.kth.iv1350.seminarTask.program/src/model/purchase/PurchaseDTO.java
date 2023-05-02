package model.purchase;

import java.util.ArrayList;

public class PurchaseDTO {

    private ArrayList<ItemWithQuantity> registeredItems;
    private double  totalVAT;
    private double runningTotal;


    public PurchaseDTO(SaleLog saleLog){
        this.registeredItems = saleLog.getListOfSoldItems();
        this.runningTotal = saleLog.getRunningTotal().getAmountInclVAT();
        this.totalVAT = saleLog.getTotalVAT().getAmount();
    }

    public ArrayList<ItemWithQuantity> getRegisteredItems() {
        return registeredItems;
    }

    public double getRunningTotal() {
        return runningTotal;
    }

    public double getTotalVAT() {
        return totalVAT;
    }
}
