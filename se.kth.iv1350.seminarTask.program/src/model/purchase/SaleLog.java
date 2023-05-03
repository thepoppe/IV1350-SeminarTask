package model.purchase;

import integration.discounts.DiscountDTO;
import integration.inventory.ItemDTO;

import java.util.ArrayList;

class SaleLog {

    private final TotalPrice runningTotal;
    private final TotalVAT totalVAT;

    private ArrayList<ItemWithQuantity> listOfRegisteredItems;


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
    ArrayList<ItemWithQuantity> getListOfSoldItems(){
        return listOfRegisteredItems;
    }


    /**
     * addItemToSaleLog adds the registered item to the ArrayList and updates the price and total VAT
     * @param item - refers to the item collected from inventory
     * @param quantity - refers to the quantity of the wanted item
     */
    void addItemToSaleLog(ItemDTO item, int quantity) {

        int indexOfItem = findItemIndex(item);
        updateQuantity(indexOfItem,quantity);
        runningTotal.addItemPrice(item, quantity);
        totalVAT.addItemVAT(item, quantity);

    }


    /**
     * searches through all the items in the ArrayList. If the identifier provided by the discount list
     * is equal to one of the identifiers in the list of item. that item's discount attribute is updated.
     * Price and vat is then updated.
     * @param discounts - discounts refers to a list of discount collected by DiscountHandler.
     */
    void addDiscountToSaleLog(ArrayList<DiscountDTO> discounts) {

        //Searches through the arraylist after identifiers and adds Discounts to the ItemWithQuantity.
        for (DiscountDTO discountOnItem: discounts) {
            for (ItemWithQuantity registeredItem : listOfRegisteredItems) {
                    if (discountOnItem.getItemIdentifier() == registeredItem.getItem().getIdentifier())
                        registeredItem.setDiscount(discountOnItem.getDiscount());
                }
        }
        runningTotal.updatePriceAfterDiscounts(listOfRegisteredItems);
        totalVAT.calculateTotalVATAfterDiscounts(listOfRegisteredItems);
    }


    /**
     * private helper method to find the index of an item in the array list. If identifier is not found a new
     * ItemWithQuantity is created and added to the List.
     * @param item - refers to the item collected from inventory
     * @return - returns the index of the added item.
     */
    private int findItemIndex(ItemDTO item){
        boolean itemIsRegistered = false;
        int i = 0;
        while (!itemIsRegistered && i < listOfRegisteredItems.size()){
            ItemDTO theItem = listOfRegisteredItems.get(i).getItem();
            if (theItem.getIdentifier() == item.getIdentifier())
                itemIsRegistered = true;
            else i++;
        }

        if (!itemIsRegistered && listOfRegisteredItems.size() > 0)
            listOfRegisteredItems.add(new ItemWithQuantity(item));

        else if(!itemIsRegistered)
            listOfRegisteredItems.add(new ItemWithQuantity(item));

        return i;
    }


    /**
     * private helper method to update quantity of a registered Item in the list
     * @param indexOfSelectedItem - the index in the List of the item to be updated
     * @param quantityOfSelectedItem - the quantity to increase with
     */
    private void updateQuantity(int indexOfSelectedItem, int quantityOfSelectedItem){
        listOfRegisteredItems.get(indexOfSelectedItem).addToQuantity(quantityOfSelectedItem);
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
     * @return - returns the instance totalVAT
     */
    TotalVAT getTotalVAT() {
        return totalVAT;
    }
}
