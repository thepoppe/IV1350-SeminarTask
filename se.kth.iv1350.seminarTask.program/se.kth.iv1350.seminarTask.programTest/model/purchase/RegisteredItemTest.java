package model.purchase;

import integration.inventory.ItemDTO;
import static org.junit.jupiter.api.Assertions.*;

class RegisteredItemTest {

    RegisteredItem firstItem;
    RegisteredItem secondItem;
    ItemDTO testItem;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testItem = new ItemDTO(9,"Banana", 5.0, 0.25);
        firstItem = new RegisteredItem(testItem,1);
        secondItem = new RegisteredItem(new ItemDTO(8, "apple", 5,0.3),1);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        firstItem = null;

        testItem = null;
    }


    @org.junit.jupiter.api.Test
    void addToQuantity() {
        firstItem.addToQuantity(1);
        int expectedResult = 2;
        int result = firstItem.getQuantity();
        assertEquals(expectedResult,result, "quantity is not changed");
    }


    @org.junit.jupiter.api.Test
    void removeFromQuantity() {
        firstItem.removeFromQuantity(1);
        int expectedResult = 0;
        int result = firstItem.getQuantity();
        assertEquals(expectedResult,result, "quantity is not changed");
    }


    @org.junit.jupiter.api.Test
    void setDiscount() {
        double expectedResult = 5;
        firstItem.setDiscount(5);
        double result = firstItem.getDiscount();
        assertEquals(expectedResult,result, "Discount is not changed");
    }


    @org.junit.jupiter.api.Test
    void isEqualTo() {
        boolean expectedResult = false;
        boolean result = firstItem.isEqualTo(secondItem);
        assertEquals(expectedResult,result, "Two different objects are identified as the same");

    }

}