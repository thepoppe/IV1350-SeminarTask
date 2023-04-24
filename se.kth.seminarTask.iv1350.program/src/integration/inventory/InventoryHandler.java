package integration.inventory;


import model.purchase.PurchaseDTO;

public class InventoryHandler {



    public InventoryHandler(){

    }

    public ItemDTO fetchItemFromInventory(int identifier, int quantity){

        ItemDTO itemToReturn = searchForItem();
        return null; //temporary
    }

    public void registerSoldItems(PurchaseDTO purchaseInformation) {

        //updates database quantity of specified items.

    }

}
