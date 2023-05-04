package model.payment;

import model.purchase.PurchaseDTO;

/**
 * The class ChangeDTO is the information needed for the user to know how much change to return to the customer
 */
public class ChangeDTO {

    double amount;

    /**
     * Constructor for the class ChangeDTO
     * @param paidAmount - the amount paid by the customer
     * @param purchaseInformation - PurchaseDTO with all information about the purchase
     */
    ChangeDTO(double paidAmount, PurchaseDTO purchaseInformation){
        double totalPrice = purchaseInformation.getRunningTotal();
        this.amount = Math.abs(paidAmount-totalPrice);
    }


    /**
     * getter for the amount of change to be returned to the customer
     * @return - a double of the amount to give to customer
     */
    public double getAmount(){
        return this.amount;
    }
}
