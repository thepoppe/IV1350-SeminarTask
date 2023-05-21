package model.purchase;

import integration.discounts.DiscountDTO;
import integration.inventory.ItemDTO;

import java.util.ArrayList;

/**
 * SaleLog contains of a list of registered items and one instance of the class TotalPrice and TotalVAT
 * This class is package private and internally keeps track of the items added to the purchase and the cost for these.
 */
class SaleLog {

    private final TotalPrice runningTotal;
    private final TotalVAT totalVAT;

    private final ArrayList<RegisteredItem> listOfRegisteredItems;


    /**
     * default constructor creates an instance of TotalPrice and TotalVAT
     */
    SaleLog(){
        this.runningTotal = new TotalPrice();
        this.totalVAT = new TotalVAT();
        this.listOfRegisteredItems = new ArrayList<>();
    }


    /**
     * Getter method for the list of registered items
     * @return - returns the list of registered items and their quantity
     */
    ArrayList<RegisteredItem> getListOfSoldItems(){
        return listOfRegisteredItems;
    }


    /**
     * Getter for runningTotal which is an instance of TotalPrice
     * @return - returns the instance runningTotal
     */
    TotalPrice getRunningTotal() {
        return runningTotal;
    }


    /**
     * Getter for totalVAT which is an instance of TotalVAT
     * @return returns the instance totalVAT
     */
    TotalVAT getTotalVAT() {
        return totalVAT;
    }


    /**
     * addItemToSaleLog adds the registered item to the ArrayList and updates the price and total VAT
     * @param itemToAdd refers to the item collected from inventory
     * @param quantityOfItemToAdd refers to the quantity of the wanted item
     */
    void addItemToSaleLog(ItemDTO itemToAdd, int quantityOfItemToAdd) {

        int indexOfItem = findItemIndex(itemToAdd);
        updateQuantity(indexOfItem,quantityOfItemToAdd);
        updatePriceInfo(itemToAdd, quantityOfItemToAdd);

    }


    /**
     * private helper method to find the index of an item in the array list. If identifier is not found a new
     * ItemWithQuantity is created and added to the List.
     * @param itemToAdd refers to the item collected from inventory
     * @return returns the index of the added item.
     */
    private int findItemIndex(ItemDTO itemToAdd){
        boolean itemIsRegistered = false;
        int indexOfItemToAdd = 0;
        while (!itemIsRegistered && indexOfItemToAdd < listOfRegisteredItems.size()){
            ItemDTO theItem = listOfRegisteredItems.get(indexOfItemToAdd).getItem();
            if (theItem.getIdentifier() == itemToAdd.getIdentifier())
                itemIsRegistered = true;
            else indexOfItemToAdd++;
        }

        if(!itemIsRegistered)
            listOfRegisteredItems.add(new RegisteredItem(itemToAdd));

        return indexOfItemToAdd;
    }


    /**
     * private helper method to update quantity of a registered Item in the list
     * @param indexOfSelectedItem the index in the List of the item to be updated
     * @param quantityOfSelectedItem the quantity to increase with
     */
    private void updateQuantity(int indexOfSelectedItem, int quantityOfSelectedItem){
        listOfRegisteredItems.get(indexOfSelectedItem).addToQuantity(quantityOfSelectedItem);
    }


    /**
     * Updates the price and the total Vat with the cost for the added items.
     * @param itemToAdd the item to be added
     * @param quantityOfItemToAdd the quantity of the item
     */
    private void updatePriceInfo(ItemDTO itemToAdd,int quantityOfItemToAdd){
        runningTotal.addItemPrice(itemToAdd, quantityOfItemToAdd);
        totalVAT.addItemVAT(itemToAdd, quantityOfItemToAdd);
    }


    /**
     * adds the collected discount to the registered items
     * @param discounts discounts refers to a list of discount collected by DiscountHandler.
     */
    void addDiscountToSaleLog(ArrayList<DiscountDTO> discounts) {
        setDiscountOnItems(discounts);
        updatePriceAfterDiscount(this.listOfRegisteredItems);
    }


    /**
     * searches through all the items in the ArrayList. If the identifier provided by the discount list
     * is equal to one of the identifiers in the list of item. that item's discount attribute is updated.
     *
     * @param discounts discounts refers to a list of discount collected by DiscountHandler.
     */
    private void setDiscountOnItems(ArrayList<DiscountDTO> discounts){
        for (DiscountDTO discountOnItem: discounts) {
            for (RegisteredItem registeredItem : this.listOfRegisteredItems) {
                if (discountOnItem.getItemIdentifier() == registeredItem.getItem().getIdentifier())
                    registeredItem.setDiscount(discountOnItem.getDiscount());
            }
        }
    }


    /**
     * This method updates the total price and the total vat with the lower price if a discount is set
     * @param listOfRegisteredItems the list of all registered items
     */
    private void updatePriceAfterDiscount(ArrayList<RegisteredItem> listOfRegisteredItems){
        runningTotal.updatePriceAfterDiscounts(listOfRegisteredItems);
        totalVAT.calculateTotalVATAfterDiscounts(listOfRegisteredItems);
    }

}
