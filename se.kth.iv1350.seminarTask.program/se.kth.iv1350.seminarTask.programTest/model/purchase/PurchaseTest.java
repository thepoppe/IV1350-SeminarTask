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
    final ItemDTO firstItem = new ItemDTO(9,"Banana", 5.0, 0.25);
    final int firstQuantity = 2;
    ArrayList<DiscountDTO> listOfTestDiscounts;



    @BeforeEach
    void setUp() {
        purchaseTest = new Purchase();
        listOfTestDiscounts = new ArrayList<>();
        listOfTestDiscounts.add(new DiscountDTO(9, 5));
    }

    @AfterEach
    void tearDown() {
        purchaseTest = null;
        listOfTestDiscounts = null;
    }


    @Test
    void addItemToPurchase() {
        PurchaseDTO purchaseBefore = purchaseTest.getPurchaseDTO();
        purchaseTest.addItemToPurchase(firstItem,firstQuantity);

        PurchaseDTO purchaseAfter = purchaseTest.getPurchaseDTO();
        assertNotEquals(purchaseBefore,purchaseAfter,"Purchase information was not updated");
    }


    @Test
    void addDiscount() {
        purchaseTest.addItemToPurchase(firstItem,firstQuantity);
        double priceBefore = purchaseTest.getPurchaseDTO().getRunningTotal();
        purchaseTest.addDiscount(listOfTestDiscounts);
        double priceAfter = purchaseTest.getPurchaseDTO().getRunningTotal();
        assertNotEquals(priceBefore, priceAfter, "Discount did not reduce total price");
    }
}