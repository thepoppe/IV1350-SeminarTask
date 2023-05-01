package model.purchase;

import integration.inventory.ItemDTO;

import java.util.ArrayList;

class TotalVAT {

    private double amount;

    TotalVAT(){
        this.amount = 0;
    }

    double getAmount() {
        return amount;
    }

    void addItemVAT(ItemDTO item){
        amount += calculateVAT(item.getPrice(), item.getVAT());
    }

    void updateTotalVATAfterDiscounts( ArrayList<SoldItem> soldItems) {
        this.amount = 0;
        for (SoldItem registeredItem : soldItems) {
            double itemPrice = registeredItem.getItem().getPrice() - registeredItem.getDiscount();
            double itemVAT = registeredItem.getItem().getVAT();
            this.amount +=  calculateVAT(itemPrice,itemVAT);
        }
    }

    private double calculateVAT(double price, double VATRate){
        return price * VATRate;
    }
}
