package model.purchase;

import integration.inventory.ItemDTO;

import java.util.ArrayList;

class TotalPrice {
    double amountInclVAT;

    /**
     * default constructor create an instance of TotalPrice with base value 0
     */
    TotalPrice(){
        this.amountInclVAT = 0;
    }

    /**
     * getAmountInclVAT returns the amount stored in "amountInclVAT"
     * @return - returns a double
     */
    double getAmountInclVAT(){
        return amountInclVAT;
    }

    /**
     * addItemPrice calculates the total amount of selected items and adds the
     * price including vat to amountInclVAT
     * @param itemToBeAdded - the item that is added to the purchase
     * @param quantity - specifies how many quantities of the item
     */
    void addItemPrice(ItemDTO itemToBeAdded, int quantity){

        double amountToAdd = itemToBeAdded.getPrice() * quantity;
        double amountOfVATtoAdd = amountToAdd * itemToBeAdded.getVAT();
        this. amountInclVAT += (amountToAdd + amountOfVATtoAdd);
    }

    /**
     * If a discount is requested updatePriceAfterDiscounts calculates the new
     * total price of each item based on price and discount. This is done for
     * all items that has been registered. amountInclVAT is then set to the amount.
     * @param registeredItems - contains a list with all registered item for the purchase.
     */
    void updatePriceAfterDiscounts(ArrayList<ItemWithQuantity> registeredItems){

        double amountToAdd = 0;
        double amountOfVATToAdd = 0;
        for (ItemWithQuantity item : registeredItems) {
            amountToAdd +=  (item.getItem().getPrice() - item.getDiscount()) * item.getQuantity();
            amountOfVATToAdd += amountToAdd * item.getItem().getVAT();
            this.amountInclVAT += amountToAdd + amountOfVATToAdd;
        }
        this.amountInclVAT = amountToAdd + amountOfVATToAdd;
    }


}
