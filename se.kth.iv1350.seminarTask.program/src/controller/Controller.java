package controller;
import integration.payment.AccountingSystem;
import integration.discounts.DiscountDTO;
import integration.discounts.DiscountHandler;
import integration.inventory.InventoryHandler;
import integration.inventory.ItemDTO;
import model.payment.ChangeDTO;
import model.payment.Payment;
import model.payment.PaymentDTO;
import model.payment.ReceiptDTO;
import model.purchase.Purchase;
import model.purchase.PurchaseDTO;

import java.util.ArrayList;

public class Controller {

    private final InventoryHandler inventoryHandler;
    private final DiscountHandler discountHandler;
    private final AccountingSystem accounting;
    private Purchase currentPurchase;
    private Payment payment;


    // constructor for Controller
    public Controller( InventoryHandler inventoryHandler,
                       DiscountHandler discountHandler,
                       AccountingSystem accounting ){

        this.inventoryHandler = inventoryHandler;
        this.discountHandler = discountHandler;
        this.accounting = accounting;

    }



    public void startSale(){
        double registerAmount;
        registerAmount = accounting.getRegisterAmount();

        this.payment = new Payment(registerAmount);

        this.currentPurchase = new Purchase();

    }





    public PurchaseDTO enterItemInfo(int identifier, int quantity) {

        ItemDTO item = inventoryHandler.fetchItemFromInventory(identifier, quantity);
        currentPurchase.updatePurchase(item, quantity);

        return currentPurchase.getPurchaseDTO();

    }


    public PurchaseDTO requestDiscount(int customerID) {
        PurchaseDTO soldItems = currentPurchase.getPurchaseDTO();
        ArrayList<DiscountDTO> discounts = discountHandler.fetchAvailableDiscounts(customerID, soldItems);

        currentPurchase.addDiscount(discounts);

        return currentPurchase.getPurchaseDTO();

    }


    public ChangeDTO enterPayment(double paidAmount) {

        PurchaseDTO purchaseInformation = currentPurchase.getPurchaseDTO();

        payment.paymentForItems(paidAmount, purchaseInformation);
        return payment.getChange();

    }

    public ReceiptDTO endSale() {
        PaymentDTO paymentInfo = payment.getPaymentInfo();
        ReceiptDTO receipt = payment.getReceipt();
        PurchaseDTO purchaseInformation = currentPurchase.getPurchaseDTO();

        accounting.registerPayment(paymentInfo);
        inventoryHandler.registerSoldItems(purchaseInformation);

        return receipt;

    }
}
