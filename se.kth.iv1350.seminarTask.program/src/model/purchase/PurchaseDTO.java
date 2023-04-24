package model.purchase;

import java.util.ArrayList;

public class PurchaseDTO {

    private ArrayList<SoldItem> registeredItems;

    private double runningTotal;


    public PurchaseDTO(SaleLog saleLog){
        this.registeredItems = saleLog.getListOfSoldItems();
    }

    public ArrayList<SoldItem> getRegisteredItems() {
        return registeredItems;
    }

    public double getRunningTotal() {
        return runningTotal;
    }
}
