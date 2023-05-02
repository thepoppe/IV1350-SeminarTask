package model.purchase;

import integration.inventory.ItemDTO;

import java.util.ArrayList;

class TotalPrice {
    double amountInclVAT;

    /**
     * default constructor
     */
    TotalPrice(){
        this.amountInclVAT = 0;
    }


    double getAmountInclVAT(){
        return amountInclVAT;
    }

    void addItemPrice(ItemDTO itemToBeAdded, int quantity){
        double amountToAdd = itemToBeAdded.getPrice() * quantity;
        double amountOfVATtoAdd = amountToAdd * itemToBeAdded.getVAT();
        this. amountInclVAT += amountToAdd + amountOfVATtoAdd;
    }

    void updatePriceAfterDiscounts(ArrayList<ItemWithQuantity> registeredItems){
        double amountToAdd = 0;
        double amountOfVATToAdd = 0;
        for (ItemWithQuantity item : registeredItems) {
            amountToAdd +=  (item.getItem().getPrice() - item.getDiscount()) * item.getQuantity();
            amountOfVATToAdd += amountToAdd * item.getItem().getVAT();
        }
        this.amountInclVAT = amountToAdd + amountOfVATToAdd;
    }

}
