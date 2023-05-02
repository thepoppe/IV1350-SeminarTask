package model.purchase;

import integration.inventory.ItemDTO;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TotalVATTest {
    TotalVAT defautTotalVAT;
    ItemWithQuantity firstItem;
    ItemWithQuantity secondItem;
    ArrayList<ItemWithQuantity> testItems;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        defautTotalVAT = new TotalVAT();
        firstItem = new ItemWithQuantity(new ItemDTO(9,"Banana", 5.0, 0.25), 1);
        secondItem = new ItemWithQuantity(new ItemDTO(8,"Apple", 10.0, 0.25), 2);
        testItems = new ArrayList<>();
        testItems.add(firstItem);
        testItems.add(secondItem);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        defautTotalVAT = null;
        firstItem = null;
        secondItem = null;
        testItems= null;
    }

    @org.junit.jupiter.api.Test
    void getAmountWhenEmpty() {
        double expectedResult = 0;
        double result = defautTotalVAT.getAmount() + defautTotalVAT.getAmount();
        assertEquals(expectedResult,result, "recieves wrong inital-value from contructor");
    }

    @org.junit.jupiter.api.Test
    void addItemVAT() {
        for (ItemWithQuantity item : testItems) {
            defautTotalVAT.addItemVAT(item.getItem(), item.getQuantity());
        }
        double expectedResult = 5*0.25 + 10*2 *0.25;
        double result = defautTotalVAT.getAmount();
        assertEquals(expectedResult,result, "wrong amount of VAT is calculated from Items");

    }

    @org.junit.jupiter.api.Test
    void updateTotalVATAfterDiscounts() {
        firstItem.setDiscount(5);
        secondItem.setDiscount(5);
        defautTotalVAT.calculateTotalVATAfterDiscounts(testItems);
        double expectedResult = (5-5)*0.25 + (10-5)*2 *0.25;
        double result = defautTotalVAT.getAmount();
        assertEquals(expectedResult,result, "wrong amount of VAT is calculated from Items");

    }
}