package controller;

import model.payment.PaymentException;
import observer.PurchaseObserver;
import integration.ExternalHandlerCreator;
import integration.inventory.EnteredItemInfoDTO;
import integration.inventory.ItemDTO;
import integration.inventory.ItemNotAvailableException;
import model.payment.ChangeDTO;
import model.purchase.RegisteredItem;
import model.purchase.PurchaseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controllerTest;
    @BeforeEach
    void setUp() throws IOException {
        controllerTest = new Controller(new ExternalHandlerCreator(), new PurchaseObserver());
        RegisteredItem[] testInventory = {new RegisteredItem(new ItemDTO(10,"Test",10,0),10)};
        controllerTest.createTestInventory(testInventory);
        controllerTest.createTestDiscount(1000,10,5);

    }

    @AfterEach
    void tearDown() {
        controllerTest = null;

    }


    @Test
    void enterValidItemInfo() throws PurchaseException, ItemNotAvailableException {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        assertNotNull(info, "valid identifier returns null");
    }
    @Test
    void enterNotValidItemInfo() throws PurchaseException {
        boolean correctExceptionThrown = false;
        try{
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(1,1));
        }
        catch( ItemNotAvailableException exc){
            correctExceptionThrown = true;

        }
        assertTrue(correctExceptionThrown, "An invalid item identifier did not result in an ItemNotAvailableException");
    }


    @Test
    void requestDiscountValidCustomer() throws PurchaseException, ItemNotAvailableException {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        double priceBeforeDiscount = info.getRunningTotal();
        info = controllerTest.requestDiscount(1000);
        double priceAfterDiscount = info.getRunningTotal();
        assertNotEquals(priceBeforeDiscount,priceAfterDiscount,"Price is not reduced by the discount");
    }

    @Test
    void requestDiscountNotValidCustomer() throws PurchaseException, ItemNotAvailableException {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        double priceBeforeDiscount = info.getRunningTotal();
        info = controllerTest.requestDiscount(10);
        double priceAfterDiscount = info.getRunningTotal();
        assertEquals(priceBeforeDiscount,priceAfterDiscount,"Price is reduced for wrong customer");
    }

    @Test
    void enterPayment() throws PurchaseException, ItemNotAvailableException, PaymentException {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        ChangeDTO change = controllerTest.enterPayment(100);
        double expectedChange = 100 - info.getRunningTotal();
        double returnedChange = change.getAmount();
        assertEquals(expectedChange,returnedChange, "change is not calculate by payment");

    }

    //Cant be tested no access due to encapsulation. View cant access the accountingSystem from controller
    @Test
    void shareInformationWithExternalSystemsTest() throws PurchaseException, ItemNotAvailableException, PaymentException {
        controllerTest.startSale();
        PurchaseDTO info = controllerTest.enterItemInfo(new EnteredItemInfoDTO(10,1));
        ChangeDTO change = controllerTest.enterPayment(100);
        controllerTest.endSale();

    }
     @Test
    void testTheUIDisplayException() throws ItemNotAvailableException {

        String expectedMessageToView = "Something went wrong with the request, contact support.\n";
         try {
             controllerTest.enterItemInfo(new EnteredItemInfoDTO(-1, 1));
         } catch (PurchaseException e) {
             assertEquals(expectedMessageToView,e.getMessage(),"The incorrect error message is sent to the view");
         }

     }



}