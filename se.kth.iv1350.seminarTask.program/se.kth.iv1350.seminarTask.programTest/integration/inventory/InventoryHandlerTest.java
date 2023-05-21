package integration.inventory;

import integration.FailedToConnectToDatabaseException;
import model.purchase.RegisteredItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryHandlerTest {

    ItemDTO wantedItem;
    InventoryHandler inventory;
    final int searchedIdentifier = 1;
    final int invalidIdentifier = 99999;
    @BeforeEach
    void setUp() {
        inventory = new InventoryHandler();
        wantedItem = new ItemDTO(1,"Test", 1,0);
        inventory.addItemsToStock(new RegisteredItem[]{new RegisteredItem(wantedItem,100)});


    }

    @AfterEach
    void tearDown() {
        inventory = null;
        wantedItem = null;

    }

    @Test
    void fetchItemFromInventory() throws FailedToConnectToDatabaseException, ItemNotAvailableException {
        ItemDTO fetchedItem = inventory.fetchItemFromInventory(new EnteredItemInfoDTO(searchedIdentifier,1));

        assertEquals(fetchedItem,wantedItem, " items are not the same");
    }

    @Test
    void fetchInvalidItem() throws FailedToConnectToDatabaseException {
        boolean exceptionWasCaught = false;
        try {
           inventory.fetchItemFromInventory(new EnteredItemInfoDTO(invalidIdentifier,1));
        }
        catch(ItemNotAvailableException exception){
            exceptionWasCaught = true;
        }
        assertTrue( exceptionWasCaught, "more quantity requested than quantity in stock did not result in an exception");

    }

    @Test
    void fetchItemOutOfStock() throws FailedToConnectToDatabaseException {
        boolean exceptionWasCaught = false;
        try {
            inventory.fetchItemFromInventory(new EnteredItemInfoDTO(searchedIdentifier,1000
            ));
        }
        catch(ItemNotAvailableException exception){
            exceptionWasCaught = true;
        }
        assertTrue( exceptionWasCaught, "an invalid identifier does not throw an exception");

    }

    @Test
    void tryToConnectToDatabase() throws ItemNotAvailableException {
        boolean exceptionWasCaught = false;
        try {
            inventory.fetchItemFromInventory(new EnteredItemInfoDTO(-1111,1));
        }
        catch(FailedToConnectToDatabaseException exception){
            exceptionWasCaught = true;
        }
        assertTrue( exceptionWasCaught, "attempting to reach the no existing database did not result in an exception");

    }



}