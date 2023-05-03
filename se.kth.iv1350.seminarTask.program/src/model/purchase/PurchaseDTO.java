package model.purchase;

import java.util.ArrayList;

public class PurchaseDTO {

    private ArrayList<ItemWithQuantity> registeredItems;
    private double  totalVAT;
    private double runningTotal;


    /**
     * Constructor creates an instans of PurchaseDTO
     * @param saleLog - is the list of currently registered Item, the total price and the totalVat
     */
    public PurchaseDTO(SaleLog saleLog){
        this.registeredItems = saleLog.getListOfSoldItems();
        this.runningTotal = saleLog.getRunningTotal().getAmountInclVAT();
        this.totalVAT = saleLog.getTotalVAT().getAmount();
    }


    /**
     * Getter for the list of items
     * @return - An ArrayList of ItemWIthQuantity
     */
    public ArrayList<ItemWithQuantity> getRegisteredItems() {
        return registeredItems;
    }


    /**
     * getter for runningTotal
     * @return - returns a double of the total price incl VAT
     */
    public double getRunningTotal() {
        return runningTotal;
    }


    /**
     * getter for the total amount of VAT
     * @return - returns a double of the total VAT amount.
     */
    public double getTotalVAT() {
        return totalVAT;
    }
}
