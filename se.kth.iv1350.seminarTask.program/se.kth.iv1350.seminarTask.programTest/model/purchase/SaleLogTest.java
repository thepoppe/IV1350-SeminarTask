package model.purchase;

import integration.discounts.DiscountDTO;
import integration.inventory.ItemDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SaleLogTest {

    SaleLog testLog;
    final ItemDTO firstTestItem = new ItemDTO(9,"Banana", 5.0, 0.25);
    final ItemDTO secondTestItem = new ItemDTO(8,"Apple", 10.0, 0.25);
    ArrayList<DiscountDTO> discounts;
    final DiscountDTO discountOnFirstItem= new DiscountDTO(9,2);



    @BeforeEach
    void setUp() {
        testLog = new SaleLog();
        discounts = new ArrayList<>();
        discounts.add(discountOnFirstItem);
    }

    @AfterEach
    void tearDown() {
        testLog = null;
        discounts = null;

    }

    @Test
    void addFirstItem() {
        int sizeOfTheListBefore = testLog.getListOfSoldItems().size();
        testLog.addItemToSaleLog(firstTestItem, 1);
        int sizeAfterFunctionTest = testLog.getListOfSoldItems().size();
        assertNotEquals(sizeOfTheListBefore,sizeAfterFunctionTest, "item is added to the list instead of quantity increasing");
    }
    @Test
    void addAnotherItem() {
        testLog.addItemToSaleLog(firstTestItem, 1);
        int listSizeBeforeSecond = testLog.getListOfSoldItems().size();
        testLog.addItemToSaleLog(secondTestItem, 1);
        int listSizeAfterSecond = testLog.getListOfSoldItems().size();
        assertNotEquals(listSizeBeforeSecond,listSizeAfterSecond, "New item is not added to next index");
    }
    @Test
    void addAnotherItemWithSameId() {
        testLog.addItemToSaleLog(firstTestItem, 1);
        testLog.addItemToSaleLog(secondTestItem, 1);
        int listSizeBeforeThird = testLog.getListOfSoldItems().size();
        testLog.addItemToSaleLog(firstTestItem,2);
        int listSizeAfterThird = testLog.getListOfSoldItems().size();
        assertEquals(listSizeBeforeThird,listSizeAfterThird,
                "List size is increased instead of increasing the quantity of that identifier");
    }


    @Test
    void addDiscountToSaleLog() {
        testLog.addItemToSaleLog(firstTestItem, 1);
        double defaultDiscountOnFirstItem = testLog.getListOfSoldItems().get(0).getDiscount();
        testLog.addDiscountToSaleLog(discounts);
        double discountAfterMethod = testLog.getListOfSoldItems().get(0).getDiscount();
        assertNotEquals(defaultDiscountOnFirstItem, discountAfterMethod, "discount was not added to the item");
    }

    @Test
    void addDiscountOnWrongItem(){
        testLog.addItemToSaleLog(secondTestItem, 1);
        double defaultDiscountOnFirstItem = testLog.getListOfSoldItems().get(0).getDiscount();
        testLog.addDiscountToSaleLog(discounts);
        double discountAfterMethod = testLog.getListOfSoldItems().get(0).getDiscount();
        assertEquals(defaultDiscountOnFirstItem, discountAfterMethod, "discount was applied on wrong identifier");
    }
}