package integration.inventory;

import model.purchase.PurchaseDTO;
import model.purchase.ItemWithQuantity;

import java.util.ArrayList;


public class InventoryHandler {

    private ArrayList<ItemWithQuantity> itemsInInventory;

    public InventoryHandler(){
        this.itemsInInventory = new ArrayList<>();
    }

    public ItemDTO fetchItemFromInventory(EnteredItemInfoDTO registeredItemInfo){

        int indexForSelectedItem =  findIndexForItem(registeredItemInfo.getIdentifier());
        boolean itemsInStock = verifyItemAvailability(indexForSelectedItem, registeredItemInfo.getQuantity());
        ItemDTO selectedItem = null;
        if (itemsInStock) {
            selectedItem = itemsInInventory.get(indexForSelectedItem).getItem();
            removeFromInventory(indexForSelectedItem, registeredItemInfo.getQuantity());
        }
        return selectedItem;
    }

    private void removeFromInventory(int indexOfTheItem, int quantityToRemove) {
        this.itemsInInventory.get(indexOfTheItem).removeFromQuantity(quantityToRemove);
        if (this.itemsInInventory.get(indexOfTheItem).getQuantity() == 0)
            this.itemsInInventory.remove(indexOfTheItem);
    }


    private boolean verifyItemAvailability(int indexForItem, int quantityOfItem){
        return itemsInInventory.get(indexForItem).getQuantity() >= quantityOfItem;
    }

    private  int findIndexForItem(int requestedItemIdentifier){
        int indexForFoundItem = -1;
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (itemsInInventory.get(i).getItem().getIdentifier() == requestedItemIdentifier)
                indexForFoundItem = i;
        }
        return indexForFoundItem;
    }



    public void updateQuantityInInventory(PurchaseDTO purchaseInformation) {
        ArrayList <ItemWithQuantity> boughtItems =  purchaseInformation.getRegisteredItems();
        for (int i = 0; i < boughtItems.size(); i++) {
            int quantityOfCurrentItem = boughtItems.get(i).getQuantity();

            for (int j = 0; j < itemsInInventory.size(); j++) {
                if (boughtItems.get(i).isEqualTo(itemsInInventory.get(j))){
                    itemsInInventory.get(j).removeFromQuantity(quantityOfCurrentItem);
                }
            }
        }
    }


    public void addItemsToStock(ItemWithQuantity[] itemsToAddToInventory) {
        for (ItemWithQuantity item : itemsToAddToInventory) {
            itemsInInventory.add(item);
        }
    }
}

