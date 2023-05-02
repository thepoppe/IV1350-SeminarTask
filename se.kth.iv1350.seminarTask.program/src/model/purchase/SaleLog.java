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
        this.listOfRegisteredItems = new ArrayList<ItemWithQuantity>();
    }

    ArrayList<ItemWithQuantity> getListOfSoldItems(){
        return listOfRegisteredItems;
    }




    void addItemToSaleLog(ItemDTO item, int quantity) {

        int indexOfItem = findItemIndex(item);
        updateQuantity(indexOfItem,quantity);
        runningTotal.addItemPrice(item, quantity);
        totalVAT.addItemVAT(item, quantity);

    }


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


    private void updateQuantity(int indexOfSelectedItem, int quantityOfSelectedItem){

        listOfRegisteredItems.get(indexOfSelectedItem).addToQuantity(quantityOfSelectedItem);
    }

    TotalPrice getRunningTotal() {
        return runningTotal;
    }

    TotalVAT getTotalVAT() {
        return totalVAT;
    }
}
