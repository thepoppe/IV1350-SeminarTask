package model.payment;

import model.purchase.PurchaseDTO;

public class ChangeDTO {

    double amount;

    ChangeDTO(double paidAmount, PurchaseDTO purchaseInformation){

    }

    public double getAmount(){
        return this.amount;
    }
}
