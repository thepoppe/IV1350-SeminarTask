package model.purchase;

import integration.inventory.ItemDTO;

import java.util.ArrayList;

/**
 *
 */
class TotalVAT {

    private double amount;

    /**
     * creates an instance of the class TotalVAT
     */
    TotalVAT(){
        this.amount = 0;
    }


    /**
     * Method to access the amount.
     * @return returns the amount of VAT
     */
    double getAmount() {
        return amount;
    }

    /**
     * addItemVAT adds VAT amount for the last scanned item
     *
     * @param item - item is the current item to be added to the purchase.
     * @param quantity - quantity refers to the quantity of this specific item
     */
      void addItemVAT(ItemDTO item,int quantity){
        double priceForItems = item.getPrice() * quantity;
        amount += calculateVAT(priceForItems, item.getVAT());
    }


    /**
     * Method calculates the new VAT if discounts are applied to the purchase. The method goes the list of registered
     * items and sums up the new Total.
     *
     * @param registeredItems - registered Items is an ArrayList containing the registered Items and their quantities
     */
    void calculateTotalVATAfterDiscounts(ArrayList<ItemWithQuantity> registeredItems) {
        this.amount = 0;
        for (ItemWithQuantity currentItem : registeredItems) {
            double reducedPrice = currentItem.getItem().getPrice() - currentItem.getDiscount();
            double priceForAllNoOfItem = reducedPrice * currentItem.getQuantity();
            double itemVAT = currentItem.getItem().getVAT();
            this.amount +=  calculateVAT(priceForAllNoOfItem,itemVAT);
        }
    }


    /**
     * calculates the amount of VAT for the current item
     *
     * @param price - The price for the selected items
     * @param VATRate - The Vat rate for the selected items
     * @return The calculated vat based of price and VATRate is returned
     */
    private double calculateVAT(double price, double VATRate){
        return price * VATRate;
    }



}
