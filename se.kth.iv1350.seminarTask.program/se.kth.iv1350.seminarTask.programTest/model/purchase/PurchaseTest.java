package model.purchase;

import integration.discounts.DiscountDTO;
import integration.inventory.ItemDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseTest {
    Purchase purchaseTest;
    ItemDTO firstItem = new ItemDTO(9,"Banana", 5.0, 0.25);
    ItemDTO secondItem = new ItemDTO(8,"Apple", 10.0, 0.25);
    int firstQuantity = 2;
    int secondQuantity = 1;
    int thirdQuantity = 1;
    ArrayList<DiscountDTO> listOfTestDiscounts;



    @BeforeEach
    void setUp() {
        purchaseTest = new Purchase();
        listOfTestDiscounts = new ArrayList<>();
        listOfTestDiscounts.add(new DiscountDTO(8, 5));
    }

    @AfterEach
    void tearDown() {
        purchaseTest = null;

        listOfTestDiscounts = null;
    }


    @Test
    void addItemToPurchase() {

        purchaseTest.addItemToPurchase(firstItem,firstQuantity);
        int expectedSize = 1;
        int actualSizeOfList = purchaseTest.getSaleLog().getListOfSoldItems().size();
        assertEquals(expectedSize,actualSizeOfList,"Item was not added");
    }

    @Test
    void getPurchaseDTOWitItems() {
        purchaseTest.addItemToPurchase(firstItem,firstQuantity);
        assertNotNull(purchaseTest.getPurchaseDTO(),"DTO contains information when it should be empty");
    }


    @Test
    void addAnotherItem() {

        purchaseTest.addItemToPurchase(firstItem,firstQuantity);
        purchaseTest.addItemToPurchase(secondItem,secondQuantity);
        int sizeBefore = 1;
        int actualSizeOfList = purchaseTest.getSaleLog().getListOfSoldItems().size();
        assertNotEquals(sizeBefore,actualSizeOfList,"Item was not added to the list");
    }
    @Test
    void addItemWithSameID() {

        purchaseTest.addItemToPurchase(firstItem,firstQuantity);
        purchaseTest.addItemToPurchase(secondItem,secondQuantity);
        purchaseTest.addItemToPurchase(firstItem, thirdQuantity);
        int wrongArraySize = 3;
        int actualSizeOfList = purchaseTest.getSaleLog().getListOfSoldItems().size();
        assertNotEquals(wrongArraySize,actualSizeOfList,"Third Item was added to the array instead of quantity increased");
    }

    @Test
    void addDiscount() {
        purchaseTest.addItemToPurchase(firstItem,firstQuantity);
        purchaseTest.addItemToPurchase(secondItem,secondQuantity);
        purchaseTest.addItemToPurchase(firstItem, thirdQuantity);
        double priceBefore = purchaseTest.getPurchaseDTO().getRunningTotal();
        purchaseTest.addDiscount(listOfTestDiscounts);
        double priceAfter = purchaseTest.getPurchaseDTO().getRunningTotal();
        assertNotEquals(priceBefore, priceAfter, "Discount did not reduce total price");
    }
}