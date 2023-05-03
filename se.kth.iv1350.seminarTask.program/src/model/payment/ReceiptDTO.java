package model.payment;

import model.purchase.PurchaseDTO;
import model.purchase.ItemWithQuantity;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class ReceiptDTO {
    private ArrayList<ItemWithQuantity> listOfItems;
    private double runningTotal;
    private double totalVAT;
    private double change;
    private String timeAndDate;


    /**
     * Constructor, creates an instance of the Class ReceiptDTO
     * @param purchaseInformation - a PurchaseDTO containing information about the purchase
     * @param change - a ChangeDTO containing the amount of change given
     */
    ReceiptDTO(PurchaseDTO purchaseInformation, ChangeDTO change){
        this.listOfItems = purchaseInformation.getRegisteredItems();
        this.runningTotal = purchaseInformation.getRunningTotal();
        this.totalVAT = purchaseInformation.getTotalVAT();
        this.change = change.getAmount();
        LocalDateTime currentTime = java.time.LocalDateTime.now();
        this.timeAndDate =  currentTime.toString();
    }


    /**
     * getter for the list of Items purchased
     * @return - ArrayList of ItemWithQuantity
     */
    public ArrayList<ItemWithQuantity> getSoldItems() {
        return listOfItems;
    }


    /**
     * getter for the total price inc vat
     * @return - a double of the total cost for the purchase
     */
    public double getRunningTotal() {
        return runningTotal;
    }


    /**
     * getter for the total vat
     * @return - a double of the total VAT for the purchase
     */
    public double getTotalVAT() {
        return totalVAT;
    }


    /**
     * getter for the amount of change returned
     * @return - a double of the amount of change
     */
    public double getChange() {
        return change;
    }


    /**
     * getter for the time of the payment
     * @return - a String with time of the payment
     */
    public String getTimeAndDate() {
        return timeAndDate;
    }
}
