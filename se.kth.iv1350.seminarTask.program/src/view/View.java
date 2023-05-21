package view;

import controller.*;
import integration.inventory.EnteredItemInfoDTO;
import integration.inventory.ItemDTO;
import integration.inventory.ItemNotAvailableException;
import model.payment.ChangeDTO;
import model.payment.PaymentException;
import model.payment.ReceiptDTO;
import model.purchase.RegisteredItem;
import model.purchase.PurchaseDTO;
import util.ExceptionLogger;

import java.io.IOException;

/**
 * This class is a representation of the user interface for the cashier.
 */
public class View {

    private final Controller controller;
    private final ExceptionLogger exceptionLogger;
    private final TotalRevenueView incomeSubscriberToView;


    /**
     * constructor creates an instance of the class Controller
     * @param controller - the controller created in main
     */
    public View(Controller controller) throws IOException {

        this.controller = controller;
        this.exceptionLogger = new ExceptionLogger();
        this.incomeSubscriberToView = new TotalRevenueView();
    }



    /**
     * method simulates a purchase following the flow from seminar 1
     */
    public void purchaseSimulation(){
        try {
            System.out.println("A purchase is starting...\n");

            controller.startSale(this.incomeSubscriberToView);


            try{
            controller.startSale(this.incomeSubscriberToView);}
            catch(Exception e){
                System.out.println("Trying to start another sale before current is done...");
                System.out.println(e.getMessage());

            }

            RegisteredItem[] testInventory = createItemsForInventory();
            controller.createTestInventory(testInventory);

            simulateDatabaseConnectionError();

            EnteredItemInfoDTO[] testItems = createCustomerBasket();
            simulateScanningItems(testItems);
            simulateScanningInvalidIdentifier();
            simulateEnterToManyQuantity();

            controller.createTestDiscount(1337, 9, 2);
            simulateCustomerRequestDiscount(false);

            simulatePayingLessThanPrice();
            simulateGeneratingMoreChangeThanAvailable();

            simulatePayment(200);
            printReceipt(controller.collectReceipt());

            controller.endSale();
        }
        catch (PurchaseException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            exceptionLogger.storeExceptionToFile(e);
        }

    }


    private void simulateScanningInvalidIdentifier()
            throws PurchaseException {
        System.out.println("Simulates entering an invalid identifier...");
        EnteredItemInfoDTO[] testBasketFailure = {new EnteredItemInfoDTO(9999, 0)};
        simulateScanningItems(testBasketFailure);
    }

    private void simulateEnterToManyQuantity()
            throws PurchaseException {
        System.out.println("Simulates entering a quantity larger than inventory...");
        EnteredItemInfoDTO[] testBasketFailure = {new EnteredItemInfoDTO(8,100000)};
        simulateScanningItems(testBasketFailure);
    }


    private void simulateScanningItems(EnteredItemInfoDTO[] itemsToScan)
            throws  PurchaseException {

        for (EnteredItemInfoDTO scannedItem :itemsToScan) {
            try {
                PurchaseDTO purchaseInformation = this.controller.enterItemInfo(scannedItem);
                showOnScreen(purchaseInformation);
            }
            catch (ItemNotAvailableException e){
                System.out.println(e.getMessage());
            }

        }
    }

    private void simulateDatabaseConnectionError()
            throws ItemNotAvailableException {
        System.out.println("Simulates trying to reach the non existing inventory database...");
        try {
            this.controller.enterItemInfo(new EnteredItemInfoDTO(-1111, 0));
        }
        catch (PurchaseException e){
            System.out.println(e.getMessage());
        }
    }

    private void simulatePayingLessThanPrice() {
        System.out.println("Simulates paying less than the price...");
        simulatePayment(0);
    }

    private void simulateGeneratingMoreChangeThanAvailable(){
        System.out.println("Simulates paying an amount resulting in change greater than available...");
        simulatePayment(24000000);
    }
    private void simulatePayment(int amountPaid) {
        try {
            ChangeDTO change = this.controller.enterPayment(amountPaid);
            showOnScreen(change);
        }
        catch(PaymentException exception){
            System.out.println(exception.getMessage());
        }
    }


    private void simulateCustomerRequestDiscount(boolean customerAskForDiscount) {

        if (customerAskForDiscount) {
            int customerID = 1337;
            PurchaseDTO purchaseInformation = this.controller.requestDiscount(customerID);
            showOnScreen(purchaseInformation);
        }
    }




    /**
     * Creates 2 test items for the imaginary inventory
     * @return - a list of these two items and their quantity
     */
    private RegisteredItem[] createItemsForInventory(){
        return new RegisteredItem[]{
                new RegisteredItem(new ItemDTO(9,"Banana", 5.0, 0.12), 100),
                new RegisteredItem(new ItemDTO(8,"Apple", 9.0,0.12), 100)
        };
    }

    private EnteredItemInfoDTO[] createCustomerBasket(){

        return new EnteredItemInfoDTO[]{
                new EnteredItemInfoDTO(9,2),
                new EnteredItemInfoDTO(8,1),
                new EnteredItemInfoDTO(9,1)
        };

    }

    private void showOnScreen(PurchaseDTO info){
        System.out.println("Information about the purchase:");
        for (RegisteredItem item : info.getRegisteredItems()) {
            System.out.print(item.getItem().getDescription());
            System.out.print(", "+ (item.getItem().getPrice()-item.getDiscount()) +", ");
            System.out.print(item.getQuantity()+ "pc\n");
        }
        System.out.printf("Running total: %.2f\nTotal VAT: %.2f\n\n",
                info.getRunningTotal(),info.getTotalVAT());
    }


    private void showOnScreen(ChangeDTO change){
        System.out.printf("Change: %.2f\n",change.getAmount());

    }

    private void printReceipt(ReceiptDTO receipt){

        System.out.println("\nThe receipt gets printed...\n"+receipt.getTimeAndDate()+"\n");

        for (RegisteredItem item:receipt.getSoldItems()) {
            System.out.print(item.getItem().getDescription()+ "\t"+
                    (item.getItem().getPrice()-item.getDiscount()) +"kr"+
                    "\t" + item.getQuantity()+" pcs\n");
        }
        System.out.printf("Total Price: %.2f\n", receipt.getRunningTotal());
        System.out.printf("Total VAT: %.2f\n", receipt.getTotalVAT());
        System.out.printf("Change: %.2f\n\n", receipt.getChange());

    }


}

