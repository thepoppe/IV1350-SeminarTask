package integration.inventory;

import integration.FailedToConnectToDatabaseException;
import model.purchase.PurchaseDTO;
import model.purchase.RegisteredItem;

import java.util.ArrayList;

/**
 * This class is a part of the integration layer and is responsible for retrieving information about items
 * from the external inventory system. Since this project does not have a database, an ArrayList of items
 * is representing the database.
 */
public class InventoryHandler {

    private final ArrayList<RegisteredItem> itemsInInventory;


    /**
     * Constructor, creates an instance of the class InventoryHandler and creates an instance of the ArrayList
     * for testing purposes.
     */
    public InventoryHandler(){
        this.itemsInInventory = new ArrayList<>();
    }


    /**
     * fetchItemFromInventory collects information about the provided identifier from external database
     * (in this case from an ArrayList since database is not provided) and returns an ItemDTO with the information
     * @param registeredItemInfo - is the entered item identifier and the wanted quantity of this item.
     * @return - returns an ItemDTO with information about the item if available. else returns null
     */
    public ItemDTO fetchItemFromInventory(EnteredItemInfoDTO registeredItemInfo)
            throws InvalidItemIdentifierException, FailedToConnectToDatabaseException {

        connectToExternalInventory();

        int indexForSelectedItem =  findIndexForItem(registeredItemInfo.getIdentifier());
        boolean itemsInStock = verifyItemAvailability(indexForSelectedItem, registeredItemInfo.getQuantity());
        ItemDTO selectedItem = null;
        if (itemsInStock) {
            selectedItem = itemsInInventory.get(indexForSelectedItem).getItem();
            removeFromInventory(indexForSelectedItem, registeredItemInfo.getQuantity());
        }
        return selectedItem;
    }



    private void connectToExternalInventory() throws FailedToConnectToDatabaseException {
        boolean serverIsDown = true;

        if (serverIsDown)
            throw new FailedToConnectToDatabaseException("External Inventory is not online");
    }


    /**
     * private method that decreases the quantity and removes the item if the quantity is 0.
     * @param indexOfTheItem - the index of the item to have quantity decreased
     * @param quantityToRemove - the amount to decrease the quantity with
     */
    private void removeFromInventory(int indexOfTheItem, int quantityToRemove) {
        this.itemsInInventory.get(indexOfTheItem).removeFromQuantity(quantityToRemove);
        if (this.itemsInInventory.get(indexOfTheItem).getQuantity() == 0)
            this.itemsInInventory.remove(indexOfTheItem);
    }


    /**
     * checks if the wanted quantity of the item is in stock
     * @param indexForItem - the index of the wanted item
     * @param quantityOfItem - the wanted quantity of the item
     * @return - returns a boolean if item is available
     */
    private boolean verifyItemAvailability(int indexForItem, int quantityOfItem){
        return itemsInInventory.get(indexForItem).getQuantity() >= quantityOfItem;
    }


    /**
     * private method to find the index for the selected identifier. returns -1 if identifier not found in inventory
     * @param requestedItemIdentifier - the provided item identifier from the view
     * @return - returns the index containing a matching identifier.
     */
    private  int findIndexForItem(int requestedItemIdentifier) throws InvalidItemIdentifierException{
        int indexForFoundItem = -1;
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (itemsInInventory.get(i).getItem().getIdentifier() == requestedItemIdentifier)
                indexForFoundItem = i;
        }

        if (indexForFoundItem == -1) {
            throw new InvalidItemIdentifierException(
                    "The identifier \""+requestedItemIdentifier+"\" is not valid");
        }
        return indexForFoundItem;
    }


    /**
     * updateQuantityInInventory removes the bought items from the inventory
     * This should be done by external database in a real case.
     * The array itemsInInventory is a simulation of an external database.
     * @param purchaseInformation - a PurchaseDTO containing all the needed information about the purchase
     */
    public void updateQuantityInInventory(PurchaseDTO purchaseInformation) {
        ArrayList <RegisteredItem> boughtItems =  purchaseInformation.getRegisteredItems();
        for (int i = 0; i < boughtItems.size(); i++) {
            int quantityOfCurrentItem = boughtItems.get(i).getQuantity();

            for (int j = 0; j < itemsInInventory.size(); j++) {
                if (boughtItems.get(i).isEqualTo(itemsInInventory.get(j))){
                    itemsInInventory.get(j).removeFromQuantity(quantityOfCurrentItem);
                }
            }
        }
    }


    /**
     * This method is added to fill the imaginary inventory for testing
     * @param itemsToAddToInventory - the items with the quantity to be added to the inventory.
     */
    public void addItemsToStock(RegisteredItem[] itemsToAddToInventory) {
        for (RegisteredItem item : itemsToAddToInventory) {
            itemsInInventory.add(item);
        }
    }
}

