package view;

import controller.Controller;
import integration.inventory.EnteredItemInfoDTO;
import integration.inventory.ItemDTO;
import model.payment.ChangeDTO;
import model.payment.ReceiptDTO;
import model.purchase.RegisteredItem;
import model.purchase.PurchaseDTO;

/**
 * This class is a representation of the user interface for the cashier.
 */
public class View {

    private final Controller controller;


    /**
     * constructor creates an instance of the class Controller
     * @param controller - the controller created in main
     */
    public View(Controller controller){
        this.controller = controller;
    }


    /**
     * method simulates a purchase following the flow from seminar 1
     */
    public void purchaseSimulation(){
        System.out.println("Test Program Starts\n\n");

        controller.startSale();

        RegisteredItem[] testInventory = createItemsForInventory();
        controller.createTestInventory(testInventory);

        EnteredItemInfoDTO[] testItems = createCustomerBasket();

        simulateScanningItems(testItems);

        controller.createTestDiscount(1337,9,2);
        boolean customerAskForDiscount = true;
        simulateCustomerRequestDiscount(customerAskForDiscount);

        simulatePayment(200);

        controller.shareInformationWithExtSystems();

        ReceiptDTO receipt = controller.collectReceipt();
        printReceipt(receipt);

    }



    private void simulatePayment(int amountPaid) {
        ChangeDTO change = this.controller.enterPayment(amountPaid);
        showOnScreen(change);
    }


    private void simulateCustomerRequestDiscount(boolean customerAskForDiscount) {
        if (customerAskForDiscount) {
            int customerID = 1337;
            PurchaseDTO purchaseInformation = this.controller.requestDiscount(customerID);
            showOnScreen(purchaseInformation);
        }
    }


    private void simulateScanningItems(EnteredItemInfoDTO[] itemsToScan){
        for (EnteredItemInfoDTO scannedItem :itemsToScan) {
            PurchaseDTO purchaseInformation = this.controller.enterItemInfo(scannedItem);
            showOnScreen(purchaseInformation);
        }
    }




    private RegisteredItem[] createItemsForInventory(){
        return new RegisteredItem[]{
                new RegisteredItem(new ItemDTO(9,"Banana", 5.0, 0.12), 100),
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
        //show information on screen.
        System.out.println("Information about the purchase:");
        for (RegisteredItem item : info.getRegisteredItems()) {
            System.out.print(item.getItem().getDescription());
            System.out.print(", "+ (item.getItem().getPrice()-item.getDiscount()) +", ");
            System.out.print(item.getQuantity()+ "pc\n");
        }
        System.out.printf("Running total: %.2f\n" +
                "Total VAT: %.2f\n\n", info.getRunningTotal(),info.getTotalVAT());
    }


    private void showOnScreen(ChangeDTO change){
        System.out.println("Information about the change");
        System.out.println("change: "+ change.getAmount()+"\n");

    }

    private void printReceipt(ReceiptDTO receipt){

        System.out.println("The receipt gets printed...\n"+receipt.getTimeAndDate()+"\n");

        for (RegisteredItem item:receipt.getSoldItems()) {
            System.out.print(item.getItem().getDescription()+ "\t"+(item.getItem().getPrice()-item.getDiscount()) +"kr"+ "\t" + item.getQuantity()+" pcs\n");
        }
        System.out.printf("Total Price: %.2f\n", receipt.getRunningTotal());
        System.out.printf("Total VAT: %.2f\n", receipt.getTotalVAT());
        System.out.printf("Change: %.2f\n", receipt.getChange());

    }


}

