package model.purchase;

import integration.discounts.DiscountDTO;
import integration.inventory.ItemDTO;

import java.util.ArrayList;

class SaleLog {

    private final TotalPrice runningTotal;
    private final TotalVAT totalVAT;

    private ArrayList<SoldItem> listOfSoldItems;


    /**
     * default constructor creates an instance of TotalPrice and TotalVAT
     */
    SaleLog(){
        this.runningTotal = new TotalPrice();
        this.totalVAT = new TotalVAT();
        this.listOfSoldItems = new ArrayList<SoldItem>();

    }

    ArrayList<SoldItem> getListOfSoldItems(){
        return listOfSoldItems;
    }




    void addItemToSaleLog(ItemDTO item, int quantity) {

        int indexOfItem = findItemIndex(item);
        updateQuantity(indexOfItem,quantity);
        runningTotal.addItemPrice(item.getPrice(), quantity);
        totalVAT.addItemVAT(item);

    }


    void addDiscountToSaleLog(ArrayList<DiscountDTO> discounts) {

        //Searches through the arraylist after identifiers and adds Discounts to the SoldItem.
        for (DiscountDTO discountOnItem: discounts) {
            for (SoldItem registeredItem : listOfSoldItems) {
                    if (discountOnItem.getItemIdentifier() == registeredItem.getItem().getIdentifier())
                        registeredItem.setDiscount(discountOnItem.getDiscount());
                }

        }

        runningTotal.updatePriceAfterDiscounts(listOfSoldItems);
        totalVAT.updateTotalVATAfterDiscounts(listOfSoldItems);
    }


    private int findItemIndex(ItemDTO item){
        boolean itemIsRegistered = false;
        int i = 0;
        while (!itemIsRegistered && i < listOfSoldItems.size()){
            ItemDTO theItem = listOfSoldItems.get(i).getItem();
            if (theItem.getIdentifier() == item.getIdentifier())
                itemIsRegistered = true;
            else i++;
        }

        if (!itemIsRegistered && listOfSoldItems.size() > 0)
            listOfSoldItems.add(new SoldItem(item));

        else if(!itemIsRegistered)
            listOfSoldItems.add(new SoldItem(item));

        return i;
    }


    private void updateQuantity(int indexOfSelectedItem, int quantityOfSelectedItem){

        listOfSoldItems.get(indexOfSelectedItem).addToQuantity(quantityOfSelectedItem);
    }

    TotalPrice getRunningTotal() {
        return runningTotal;
    }

    TotalVAT getTotalVAT() {
        return totalVAT;
    }
}
