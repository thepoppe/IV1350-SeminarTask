package model.payment;

import integration.inventory.ItemDTO;
import model.purchase.Purchase;
import model.purchase.PurchaseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    Payment testPayment;
    PurchaseDTO purchaseInfo;
    final double amountPaidByCustomer = 100;
    @BeforeEach
    void setUp() {
        Purchase testPurchase = new Purchase();
        testPurchase.addItemToPurchase(new ItemDTO(1,"Test",10,0),1);
        purchaseInfo = testPurchase.getPurchaseDTO();
        testPayment= new Payment(100);
    }

    @AfterEach
    void tearDown() {
        testPayment = null;
        purchaseInfo = null;
    }

    @Test
    void paymentForItems() throws PaymentException {
        testPayment.paymentForItems(amountPaidByCustomer, purchaseInfo);
        double expectedChange = 90;
        double actualChange = testPayment.getChange().getAmount();
        assertEquals(expectedChange,actualChange);
    }
}