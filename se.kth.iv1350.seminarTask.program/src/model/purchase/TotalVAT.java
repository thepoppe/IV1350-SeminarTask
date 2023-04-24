package model.purchase;

import java.util.ArrayList;

class TotalVAT {

    private double amount;

    TotalVAT(){
        this.amount = 0;
    }

    double getAmount() {
        return amount;
    }

    void updateTotalVAT( ArrayList<SoldItem> soldItems) {
        // calculates and updates totalVAT
    }
}
