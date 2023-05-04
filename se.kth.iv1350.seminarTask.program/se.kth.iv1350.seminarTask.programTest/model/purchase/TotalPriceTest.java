package model.purchase;


import integration.inventory.ItemDTO;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class TotalPriceTest {
    TotalPrice defautTotalPrice;
    RegisteredItem firstItem;
    ArrayList<RegisteredItem> testItems;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        defautTotalPrice = new TotalPrice();
        firstItem = new RegisteredItem(new ItemDTO(9,"Banana", 5.0, 0.25), 1);
        testItems = new ArrayList<>();
        testItems.add(firstItem);

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        defautTotalPrice = null;
        firstItem = null;
        testItems= null;
    }


    @org.junit.jupiter.api.Test
    void addItemPrice() {
        double priceBefore = defautTotalPrice.getAmountInclVAT();
        defautTotalPrice.addItemPrice(firstItem.getItem(),firstItem.getQuantity());
        double result = defautTotalPrice.getAmountInclVAT();
        assertNotEquals(priceBefore,result, "Item price was not added");
    }


    @org.junit.jupiter.api.Test
    void updatePriceAfterDiscounts() {
        defautTotalPrice.addItemPrice(firstItem.getItem(),firstItem.getQuantity());
        double priceBefore = defautTotalPrice.getAmountInclVAT();
        firstItem.setDiscount(1);
        defautTotalPrice.updatePriceAfterDiscounts(testItems);
        double priceAfterDiscount = defautTotalPrice.getAmountInclVAT();
        assertNotEquals(priceBefore,priceAfterDiscount, "Total price is not reduced by the discount");
    }
}