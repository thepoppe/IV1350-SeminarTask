 package controller;
import model.payment.*;
import observer.PurchaseObserver;
import integration.ExternalHandlerCreator;
import integration.FailedToConnectToDatabaseException;
import integration.inventory.*;
import integration.payment.AccountingHandler;
import integration.discounts.DiscountDTO;
import integration.discounts.DiscountHandler;
import model.purchase.RegisteredItem;
import model.purchase.Purchase;
import model.purchase.PurchaseDTO;
import util.ExceptionLogger;
import view.TotalRevenueView;
import java.io.IOException;
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
    private final ExceptionLogger exceptionLogger;
    private final PurchaseObserver incomeObserver;


    /**
     * Constructor creates an instance of the class Controller
     * @param externalHandlerCreator contains instances of the external system handlers needed by the Controller
     * @param observer the instance of the observer object created in main
     */
    public Controller(ExternalHandlerCreator externalHandlerCreator, PurchaseObserver observer) throws IOException {

        this.inventoryHandler = externalHandlerCreator.getInventoryHandler();
        this.discountHandler = externalHandlerCreator.getDiscountHandler();
        this.accounting = externalHandlerCreator.getAccountingHandler();
        this.incomeObserver = observer;
        this.exceptionLogger = new ExceptionLogger();
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
      * overloaded constructor that creates an instance of classes needed for a purchase with observers
      * @param incomeToView the observer in the view
      * @throws IOException is thrown if the file writing observer cant be created in Purchase
      */
     public void startSale(TotalRevenueView incomeToView) throws IOException {
         if(currentPurchase != null || payment != null){
             exceptionLogger.storeExceptionToFile(new IllegalStateException(
                     "An instance of the class Purchase or Payment was re-initiated before the current sale was finished\n"));
             throw new IllegalStateException("An error occurred with the request.\nContact support if problem remains\n");

         }
         double registerAmount = accounting.getRegisterAmount();
         this.payment = new Payment(registerAmount);
         this.currentPurchase = new Purchase(this.incomeObserver);
         this.incomeObserver.addObserver(incomeToView);

     }


     /**
      * enterItemInfo receives the wanted item identifier and quantity. The identifier and quantity is sent to
      * the inventoryHandler that returns the item information if available. The purchase is updated with the
      * new items and new purchase information is returned. If the database is offline a message is thrown to
      * the user and a detailed message is logged for developers.
      * @param registeredItemInformation EnteredItemDTO containing the selected identifier and the quantity of this item
      * @return returns a PurchaseDTO with information about the purchase
      * @throws PurchaseException is thrown if handler fails to connect to database
      * @throws ItemNotAvailableException is thrown if item cannot be collected
      */
    public PurchaseDTO enterItemInfo(EnteredItemInfoDTO registeredItemInformation)
            throws PurchaseException, ItemNotAvailableException {

        try {
            ItemDTO collectedItem = inventoryHandler.fetchItemFromInventory(registeredItemInformation);
            currentPurchase.addItemToPurchase(collectedItem, registeredItemInformation.getQuantity());
            return currentPurchase.getPurchaseDTO();
        }
        catch (FailedToConnectToDatabaseException databaseError){
            exceptionLogger.storeExceptionToFile(databaseError);
            throw new PurchaseException("Something went wrong with the request, contact support.\n");
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
    public ChangeDTO enterPayment(double paidAmount) throws PaymentException {

        PurchaseDTO purchaseInformation = currentPurchase.getPurchaseDTO();
        payment.paymentForItems(paidAmount, purchaseInformation);
        return payment.getChange();

    }


    /**
     * shareInformationWithExt collects information about the purchase and updates the external systems before the purchase is shutdown.
     */
    private void shareInformationWithExtSystems() {

        PaymentDTO paymentInfo = payment.getPaymentInfo();
        PurchaseDTO purchaseInformation = currentPurchase.getPurchaseDTO();
        accounting.registerPayment(paymentInfo);
        inventoryHandler.updateQuantityInInventory(purchaseInformation);

    }


     /**
      * endSale updates the observer, provides information to external systems and clears the temporary observers
      */
     public void endSale() {
         shareInformationWithExtSystems();
         updateRevenueObservers();
         clearPurchaseObservers();
         this.payment =null;
         this.currentPurchase =null;

     }

     /**
      * private function to clear the current purchase's observers
      */
     private void clearPurchaseObservers() {
         this.incomeObserver.removeAllObserver();
     }

     /**
      * notifies the observers to update the total revenue with the new income from the purchase
      */
     private void updateRevenueObservers() {
         PurchaseDTO purchaseInformation = currentPurchase.getPurchaseDTO();
         incomeObserver.performPurchase(purchaseInformation.getRunningTotal());
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
