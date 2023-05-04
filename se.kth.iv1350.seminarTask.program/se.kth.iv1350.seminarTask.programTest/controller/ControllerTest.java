package controller;

import integration.ExternalHandlerCreator;
import integration.inventory.EnteredItemInfoDTO;
import integration.inventory.ItemDTO;
import model.payment.ChangeDTO;
import model.purchase.RegisteredItem;
import model.purchase.PurchaseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controllerTest;
    @BeforeEach
    void setUp() {
        controllerTest = new Controller(new ExternalHandlerCreator());
        RegisteredItem[] testInventory = {new RegisteredItem(new ItemDTO(10,"Test",10,0),10)};
        controllerTest.createTestInventory(testInventory);
        controllerTest.createTestDiscount(1000,10,5);

    }

    @AfterEach
    void tearDown() {
        controllerTest = null;

    }


    @Test
    void enterValidItemInfo() {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        assertNotNull(info, "valid identifier returns null");
    }
    @Test
    void enterNotValidItemInfo() {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(1,1));
        assertNull(info, "null not returned for invalid identifier");
    }


    @Test
    void requestDiscountValidCustomer() {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        double priceBeforeDiscount = info.getRunningTotal();
        info = controllerTest.requestDiscount(1000);
        double priceAfterDiscount = info.getRunningTotal();
        assertNotEquals(priceBeforeDiscount,priceAfterDiscount,"Price is not reduced by the discount");
    }

    @Test
    void requestDiscountNotValidCustomer() {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        double priceBeforeDiscount = info.getRunningTotal();
        info = controllerTest.requestDiscount(10);
        double priceAfterDiscount = info.getRunningTotal();
        assertEquals(priceBeforeDiscount,priceAfterDiscount,"Price is reduced for wrong customer");
    }

    @Test
    void enterPayment() {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        ChangeDTO change = controllerTest.enterPayment(100);
        double expectedChange = 100 - info.getRunningTotal();
        double returnedChange = change.getAmount();
        assertEquals(expectedChange,returnedChange, "change is not calculate by payment");

    }

    //Cant be tested no access due to encapsulation. View cant access the accountingSystem from controller
    @Test
    void shareInformationWithExternalSystemsTest() {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        ChangeDTO change = controllerTest.enterPayment(100);
        controllerTest.shareInformationWithExtSystems();

    }

}