package model.purchase;

import java.util.ArrayList;

/**
 * PurchaseDTO consist of an array with registered items, total price and total Vat. PurchaseDto
 * contains the same information as the SaleLog but is a part of the public interface and therefor is immutable
 */
public class PurchaseDTO {

    private final ArrayList<RegisteredItem> registeredItems;
    private final double  totalVAT;
    private final double runningTotal;


    /**
     * Constructor creates an instance of PurchaseDTO
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
    public ArrayList<RegisteredItem> getRegisteredItems() {
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
