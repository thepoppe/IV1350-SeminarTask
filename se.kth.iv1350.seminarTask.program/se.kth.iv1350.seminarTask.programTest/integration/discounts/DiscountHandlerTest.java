package integration.discounts;

import integration.inventory.ItemDTO;
import model.purchase.Purchase;
import model.purchase.PurchaseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DiscountHandlerTest {

    DiscountHandler discountHandler;
    ItemDTO itemToGetPriceReduced;
    int firstValidCustomer = 10;
    int secondInvalidCustomer = 0;

    Purchase purchase;
    PurchaseDTO purchaseInformation;

    @BeforeEach
    void setUp() {
        discountHandler =new DiscountHandler();
        purchase = new Purchase();
        itemToGetPriceReduced = new ItemDTO(1,"Test", 5,0);
        discountHandler.createTestDiscount(10, 1,2);
        purchase.addItemToPurchase(itemToGetPriceReduced,1);
        purchaseInformation = purchase.getPurchaseDTO();


    }

    @AfterEach
    void tearDown() {
        discountHandler = null;
        purchase = null;
        itemToGetPriceReduced = null;
    }

    @Test
    void fetchAvailableDiscountsForValidCustomer() {
        ArrayList<DiscountDTO> collectedDiscounts = discountHandler.fetchAvailableDiscounts(firstValidCustomer,purchaseInformation);
        boolean expectedResult = false;
        boolean result = collectedDiscounts.isEmpty();
        assertEquals(expectedResult,result,"Eligible discount not added");

    }
    @Test
    void fetchAvailableDiscountsForInValidCustomer() {
        ArrayList<DiscountDTO> collectedDiscounts = discountHandler.fetchAvailableDiscounts(secondInvalidCustomer,purchaseInformation);
        boolean expectedResult = true;
        boolean result = collectedDiscounts.isEmpty();
        assertEquals(expectedResult,result,"Discount was added to not eligible customer");

    }

}