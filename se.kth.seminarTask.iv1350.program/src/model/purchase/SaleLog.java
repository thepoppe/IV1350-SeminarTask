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




    void updateSaleLog(ItemDTO item, int quantity) {

        int indexOfItem = findItemIndex(item);
        updateQuantity(indexOfItem,quantity);



        runningTotal.updateTotalPrice(this.listOfSoldItems);
        totalVAT.updateTotalVAT(this.listOfSoldItems);

    }


    void updateSaleLog(ArrayList<DiscountDTO> discounts) {

        //Searches through the arraylist after identifiers and adds Discounts to the SoldItem.


        //new runningTotal is calculated
        runningTotal.updateTotalPrice(listOfSoldItems);

        totalVAT.updateTotalVAT(listOfSoldItems);
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

        if (!itemIsRegistered && listOfSoldItems.size() > 0){
            listOfSoldItems.add(new SoldItem(item));
            i++;}
        else listOfSoldItems.add(new SoldItem(item));

        return i;
    }


    private void updateQuantity(int index, int quantity){

        listOfSoldItems.get(index).addQuantity(quantity);
    }





}
