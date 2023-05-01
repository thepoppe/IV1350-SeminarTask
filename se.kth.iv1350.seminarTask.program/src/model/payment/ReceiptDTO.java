package model.payment;

import model.purchase.PurchaseDTO;
import model.purchase.SoldItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class ReceiptDTO {
    private ArrayList<SoldItem> soldItems;
    private double runningTotal;
    private double totalVAT;
    private double change;
    private String timeAndDate;


    ReceiptDTO(PurchaseDTO purchaseInformation, ChangeDTO change){
        this.soldItems = purchaseInformation.getRegisteredItems();
        this.runningTotal = purchaseInformation.getRunningTotal();
        this.totalVAT = purchaseInformation.getTotalVAT();
        this.change = change.getAmount();
        LocalDateTime currentTime = java.time.LocalDateTime.now();
        this.timeAndDate =  currentTime.toString();
    }



    public ArrayList<SoldItem> getSoldItems() {
        return soldItems;
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
