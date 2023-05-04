package model.payment;

import integration.inventory.ItemDTO;
import model.purchase.Purchase;
import model.purchase.PurchaseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {
    Register testRegister;
    double changeInRegister = 1000;
    double paidAmount = 100;
    double priceForThePurchase;
    ChangeDTO changeInformation;
    PurchaseDTO purchaseInfo;
    @BeforeEach
    void setUp() {
        Purchase testPurchase = new Purchase();
        testPurchase.addItemToPurchase(new ItemDTO(1,"Test",10,0.1),1);
        purchaseInfo = testPurchase.getPurchaseDTO();
        priceForThePurchase = testPurchase.getPurchaseDTO().getRunningTotal();
        testRegister = new Register(changeInRegister);
        changeInformation = new ChangeDTO(paidAmount, purchaseInfo);
    }

    @AfterEach
    void tearDown() {
        testRegister = null;
        purchaseInfo = null;
        changeInformation = null;
    }

    @Test
    void updateRegister() {
        double amountBeforePayment = testRegister.getRegisterAmount();
        testRegister.updateRegister(changeInformation,paidAmount);
        double amountAfterPayment = testRegister.getRegisterAmount();
        double differenceInRegister = amountAfterPayment - amountBeforePayment;
        assertEquals(differenceInRegister,priceForThePurchase,"The amount added to the register does not match the price for the purchase");
    }
}