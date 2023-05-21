package integration.inventory;

import integration.FailedToConnectToDatabaseException;
import model.purchase.PurchaseDTO;
import model.purchase.RegisteredItem;
import java.util.ArrayList;
import java.util.Collections;

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
     * fetchItemFromInventory tries to connect to the external database and collects information about the provided
     * identifier (in this case from an ArrayList since database is not provided) and returns an ItemDTO with the
     * information if given information does not exist in the list exceptions are thrown
     *
     * @param registeredItemInfo is the entered item identifier and the wanted quantity of this item.
     * @return returns an ItemDTO with information about the item if available.
     * @throws ItemNotAvailableException if identifier is not found or requested quantity is greater than available
     * @throws FailedToConnectToDatabaseException is thrown if connection to the database can not be established
     */
    public ItemDTO fetchItemFromInventory(EnteredItemInfoDTO registeredItemInfo)
            throws ItemNotAvailableException, FailedToConnectToDatabaseException {

        connectToExternalInventory(registeredItemInfo.getIdentifier());
        int indexForSelectedItem =  findIndexForItem(registeredItemInfo.getIdentifier());
        verifyItemAvailability(indexForSelectedItem, registeredItemInfo.getQuantity());

        ItemDTO selectedItem = itemsInInventory.get(indexForSelectedItem).getItem();
        removeItemQuantityFromInventory(indexForSelectedItem, registeredItemInfo.getQuantity());
        return selectedItem;

    }


    /**
     * Private method that simulates trying to connect to the database that does not exist
     * @param identifier the given identifier with negative numbers activates this test
     * @throws FailedToConnectToDatabaseException if identifier is less than 0 this
     */
    private void connectToExternalInventory(int identifier) throws FailedToConnectToDatabaseException {
        if (identifier < 0) {
            throw new FailedToConnectToDatabaseException("External Inventory is not reached");
        }

    }


    /**
     * private method that decreases the quantity of the selected item
     * @param indexOfTheItem - the index of the item to have quantity decreased
     * @param quantityToRemove - the amount to decrease the quantity with
     */
    private void removeItemQuantityFromInventory(int indexOfTheItem, int quantityToRemove) {
        this.itemsInInventory.get(indexOfTheItem).removeFromQuantity(quantityToRemove);
    }


    /**
     * checks if the requested quantity of the item is in stock, throws exception if not
     * @param indexForItem the index of the wanted item
     * @param requestedQuantity the wanted quantity of the item
     * @throws ItemNotAvailableException if requested quantity is larger than quantity in stock
     */
    private void verifyItemAvailability(int indexForItem, int requestedQuantity)
            throws ItemNotAvailableException {

        int quantityInStock = itemsInInventory.get(indexForItem).getQuantity();
        String itemDescription = itemsInInventory.get(indexForItem).getItem().getDescription();
        if (quantityInStock < requestedQuantity)
            throw new ItemNotAvailableException("The requested quantity: \"" +requestedQuantity+
                    "\" of item " +itemDescription+ " is not available\n");

    }


    /**
     * private method to find the index for the selected identifier if it is available
     * if not an exception is thrown
     * @param requestedItemIdentifier the provided item identifier from the view
     * @return  the index containing a matching identifier.
     * @throws ItemNotAvailableException if the identifier is not found in Inventory list
     */
    private  int findIndexForItem(int requestedItemIdentifier)
            throws ItemNotAvailableException{

        int indexForFoundItem = -1;
        for (int i = 0; i < itemsInInventory.size(); i++) {
            if (itemsInInventory.get(i).getItem().getIdentifier() == requestedItemIdentifier)
                indexForFoundItem = i;
        }
        if (indexForFoundItem == -1) {
            throw new ItemNotAvailableException(
                    "The identifier \""+requestedItemIdentifier+"\" is not valid\n");
        }
        return indexForFoundItem;
    }


    /**
     * updateQuantityInInventory removes the bought items from the inventory
     * This should be done by external database in a real case.
     * The array itemsInInventory is a simulation of an external database.
     * @param purchaseInformation a PurchaseDTO containing information about the purchase
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
     * @param itemsToAddToInventory the items with the quantity to be added to the inventory.
     */
    public void addItemsToStock(RegisteredItem[] itemsToAddToInventory) {
        Collections.addAll(itemsInInventory, itemsToAddToInventory);
    }
}

