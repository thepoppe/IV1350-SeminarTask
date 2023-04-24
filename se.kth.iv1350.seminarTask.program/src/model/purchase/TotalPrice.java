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


    void updateTotalPrice(ArrayList<SoldItem> soldItems){
        //calculates the total price and updates amount.
    }
}
