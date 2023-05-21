package integration.payment;


import integration.inventory.ItemDTO;
import model.payment.Payment;
import model.payment.PaymentDTO;
import model.payment.PaymentException;
import model.purchase.Purchase;
import model.purchase.PurchaseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountingHandlerTest {

    AccountingHandler accountingHandler;
    PaymentDTO paymentInfo;
    @BeforeEach
    void setUp() throws PaymentException {
        accountingHandler = new AccountingHandler();
        double regAmount = accountingHandler.getRegisterAmount();
        Payment testPayment = new Payment(regAmount);
        Purchase purchase = new Purchase();
        purchase.addItemToPurchase(new ItemDTO(1,"test", 5,0),1);
        PurchaseDTO purchaseInfo = purchase.getPurchaseDTO();
        testPayment.paymentForItems(10,purchaseInfo);
        paymentInfo = testPayment.getPaymentInfo();
    }

    @AfterEach
    void tearDown() {
        accountingHandler = null;
        paymentInfo = null;
    }

    @Test
    void registerPayment() {
        double registerBefore = accountingHandler.getRegisterAmount();
        accountingHandler.registerPayment(paymentInfo);
        double registerAfter = accountingHandler.getRegisterAmount();
        assertNotEquals(registerBefore, registerAfter, "amount in register is not update.");
    }
}