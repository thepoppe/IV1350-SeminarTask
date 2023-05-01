package integration.inventory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDTOTest {
    private ItemDTO itemDTOTest;
    private int identifierTest = 111;
    private String descriptionTest = "Apelsin";
    private double priceTest = 25.5;
    private double vatTest = 0.25;
    @BeforeEach
    void setUp() {
        itemDTOTest = new ItemDTO(identifierTest, descriptionTest, priceTest, vatTest);
    }

    @AfterEach
    void tearDown() {
        itemDTOTest = null;
    }

    @Test
    void getIdentifier() {
        int expectedResult = this.identifierTest;
        int result =itemDTOTest.getIdentifier();
        assertEquals(expectedResult,result, "Method getIdentifier returned wrong value");
    }

    @Test
    void getDescription() {
        String expectedResult = this.descriptionTest;
        String result =itemDTOTest.getDescription();
        assertEquals(expectedResult,result, "Method getDescription returned wrong value");
    }

    @Test
    void getPrice() {
        double expectedResult = this.priceTest;
        double result =  itemDTOTest.getPrice();
        assertEquals(expectedResult,result, "getPrice returned wrong value");
    }

    @Test
    void getVAT() {
        double expectedResult = this.vatTest;
        double result =  itemDTOTest.getVAT();
        assertEquals(expectedResult,result, "getVAT returned wrong value");
    }
}