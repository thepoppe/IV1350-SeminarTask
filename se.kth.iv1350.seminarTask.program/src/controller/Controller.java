 package controller;
import integration.ExternalHandlerCreator;
import integration.inventory.InvalidItemIdentifierException;
import integration.payment.AccountingHandler;
import integration.discounts.DiscountDTO;
import integration.discounts.DiscountHandler;
import integration.inventory.InventoryHandler;
import integration.inventory.ItemDTO;
import model.payment.ChangeDTO;
import model.payment.Payment;
import model.payment.PaymentDTO;
import model.payment.ReceiptDTO;
import model.purchase.RegisteredItem;
import model.purchase.Purchase;
import model.purchase.PurchaseDTO;
import integration.inventory.EnteredItemInfoDTO;

import java.util.ArrayList;

 /**
  * This class is connecting the different layers to each other.
  */
 public class Controller {

    private final InventoryHandler inventoryHandler;
    private final DiscountHandler discountHandler;
    private final AccountingHandler accounting;
    private Purchase currentPurchase;
    private Payment payment;


    /**
     * Constructor creates an instance of the class Controller
     * @param externalHandlerCreator - contains instances of the external system handlers needed by the Controller
     */
    public Controller(ExternalHandlerCreator externalHandlerCreator){

        this.inventoryHandler = externalHandlerCreator.getInventoryHandler();
        this.discountHandler = externalHandlerCreator.getDiscountHandler();
        this.accounting = externalHandlerCreator.getAccountingHandler();

    }


    /**
     * startSale create instances of the classes needed for a purchase
     */
    public void startSale(){
        double registerAmount = accounting.getRegisterAmount();
        this.payment = new Payment(registerAmount);
        this.currentPurchase = new Purchase();

    }


    /**
     * enterItemInfo receives the wanted item identifier and quantity. The identifier and quantity is sent to
     * the inventoryHandler which returns the item information if available. The item information is sent to the
     * purchase and the updated purchase is returned
     * @param registeredItemInformation - EnteredItemDTO containing the selected identifier and the quantity of this item
     * @return - returns a PurchaseDTO with information about the purchase
     */
    public PurchaseDTO enterItemInfo(EnteredItemInfoDTO registeredItemInformation) {
        try {
            ItemDTO collectedItem = inventoryHandler.fetchItemFromInventory(registeredItemInformation);
            currentPurchase.addItemToPurchase(collectedItem, registeredItemInformation.getQuantity());
            return currentPurchase.getPurchaseDTO();
        }
        catch (InvalidItemIdentifierException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }


    /**
     * Method requestDiscount receives the customer identification and collects available discount for this
     * customer. The purchase information is updated with reduced price
     * @param customerID - integer representing the customers identification
     * @return - returns a PurchaseDTO with information about the purchase
     */
    public PurchaseDTO requestDiscount(int customerID) {
        PurchaseDTO soldItems = currentPurchase.getPurchaseDTO();
        ArrayList<DiscountDTO> discounts = discountHandler.fetchAvailableDiscounts(customerID, soldItems);
        currentPurchase.addDiscount(discounts);
        return currentPurchase.getPurchaseDTO();

    }


    /**
     * enterPayment receives the paid amount for the purchase and returns the amount of change to give back to the customer
     * @param paidAmount - amount paid by the customer
     * @return - returns information about how much change to give back
     */
    public ChangeDTO enterPayment(double paidAmount) {

        PurchaseDTO purchaseInformation = currentPurchase.getPurchaseDTO();
        payment.paymentForItems(paidAmount, purchaseInformation);
        return payment.getChange();

    }


    /**
     * shareInformationWithExt collects information about the purchase and updates the external systems before the purchase is shutdown.
     */
    public void shareInformationWithExtSystems() {
        PaymentDTO paymentInfo = payment.getPaymentInfo();
        PurchaseDTO purchaseInformation = currentPurchase.getPurchaseDTO();
        accounting.registerPayment(paymentInfo);
        inventoryHandler.updateQuantityInInventory(purchaseInformation);
    }


     /**
      * collectReceipt returns the receipt to the controller
      * @return - returns the latest copy of the receipt
      */
    public ReceiptDTO collectReceipt(){
        return payment.getReceipt();
    }


    /**
     * Method created to fill the inventory with items. This should be handled by external database
     * @param itemsInStock - the items with quantity to be added to inventory
     */
    public void createTestInventory(RegisteredItem[] itemsInStock){
        inventoryHandler.addItemsToStock(itemsInStock);
    }


    /**
     * Method created to fill the discount with eligible customers for testing. This should be
     * handled by external database
     * @param approvedCustomer - integer with the customer identification number.
     */
    public void createTestDiscount(int approvedCustomer,int identifierForItem, double reductionOnPrice) {
        discountHandler.createTestDiscount(approvedCustomer,identifierForItem,reductionOnPrice);
    }
}
