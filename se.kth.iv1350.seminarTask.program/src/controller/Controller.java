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
import model.purchase.ItemWithQuantity;
import model.purchase.Purchase;
import model.purchase.PurchaseDTO;
import integration.inventory.EnteredItemInfoDTO;

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




// OBS EVENTUELL ÄNDRING inventory handler skapar ItemWithQuantity?

    public PurchaseDTO enterItemInfo(EnteredItemInfoDTO registeredItemInformation) {

        ItemDTO collectedItem = inventoryHandler.fetchItemFromInventory(registeredItemInformation);
        if (collectedItem == null)
            return null;
        else {
                                                // ItemWithQuantity = itemDTO, quantity
            currentPurchase.addItemToPurchase(collectedItem, registeredItemInformation.getQuantity());
            return currentPurchase.getPurchaseDTO();
        }
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
        inventoryHandler.updateQuantityInInventory(purchaseInformation);
        return receipt;
    }





    public void createTestInventory(ItemWithQuantity[] itemsInStock){
        inventoryHandler.addItemsToStock(itemsInStock);
    }

    public void createTestDiscount(int approvedCustomer) {
        discountHandler.createTestDiscount(approvedCustomer);
    }
}
