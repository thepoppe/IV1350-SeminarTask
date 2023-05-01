package model.payment;

import model.purchase.PurchaseDTO;

public class ChangeDTO {

    double amount;

    ChangeDTO(double paidAmount, PurchaseDTO purchaseInformation){
        double totalPrice = purchaseInformation.getRunningTotal();
        this.amount = totalPrice - paidAmount;
    }

    public double getAmount(){
        return this.amount;
    }
}
