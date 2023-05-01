package model.purchase;

import java.util.ArrayList;

class TotalPrice {
    double amount;

    /**
     * default constructor
     */
    TotalPrice(){
        this.amount = 0;
    }


    double getAmount(){
        return amount;
    }

    void addItemPrice(double amountToAdd,int quantity){
        //calculates the total price and updates amount.
        amount += amountToAdd * quantity;
    }

    void updatePriceAfterDiscounts(ArrayList<SoldItem> soldItems){
        //calculates the total price and updates amount.
        this.amount = 0;
        for (SoldItem item : soldItems) {
            this.amount +=  (item.getItem().getPrice() - item.getDiscount()) * item.getQuantity();
        }
    }
}
