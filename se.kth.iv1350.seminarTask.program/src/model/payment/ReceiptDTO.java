package model.payment;

import model.purchase.PurchaseDTO;
import model.purchase.ItemWithQuantity;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class ReceiptDTO {
    private ArrayList<ItemWithQuantity> itemWithQuantities;
    private double runningTotal;
    private double totalVAT;
    private double change;
    private String timeAndDate;


    ReceiptDTO(PurchaseDTO purchaseInformation, ChangeDTO change){
        this.itemWithQuantities = purchaseInformation.getRegisteredItems();
        this.runningTotal = purchaseInformation.getRunningTotal();
        this.totalVAT = purchaseInformation.getTotalVAT();
        this.change = change.getAmount();
        LocalDateTime currentTime = java.time.LocalDateTime.now();
        this.timeAndDate =  currentTime.toString();
    }



    public ArrayList<ItemWithQuantity> getSoldItems() {
        return itemWithQuantities;
    }

    public double getRunningTotal() {
        return runningTotal;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getChange() {
        return change;
    }

    public String getTimeAndDate() {
        return timeAndDate;
    }
}
