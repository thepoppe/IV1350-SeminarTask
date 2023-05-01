package view;

import controller.Controller;
import integration.inventory.ItemDTO;
import model.payment.ChangeDTO;
import model.payment.ReceiptDTO;
import model.purchase.SoldItem;
import model.purchase.PurchaseDTO;

public class View {


    private final Controller controller;

    public View(Controller c){
        this.controller = c;
    }



    // placeholder for methods
    public void testSale(){
        System.out.println("Program Starts...");


        controller.startSale();
        System.out.println("Method startSale OK\n\n");

        //adding items to inventory
        SoldItem[] testInventory = {
                new SoldItem(new ItemDTO(9,"Banana", 5.0, 0.25), 100),
                new SoldItem(new ItemDTO(9,"Banana", 5.0, 0.25), 100),
                new SoldItem(new ItemDTO(8,"Apple", 9.0,0.25), 100),
                new SoldItem(new ItemDTO(120,"Car stereo", 100.0, 0.40),100),
        };
        controller.createTestInventory(testInventory);
        System.out.println("Method createTestInventory OK\n\n");



        // create a list of itemIdentifier to test with
        EnteredItemInfoDTO[] testItems = {new EnteredItemInfoDTO(9,2),
                                        new EnteredItemInfoDTO(8,1),
                                        new EnteredItemInfoDTO(120,1),
                                        new EnteredItemInfoDTO(9,1)};


        // temporary loop to simulate the cashier going through the items
        PurchaseDTO purchaseInformation;
        for (EnteredItemInfoDTO scannedItem :testItems) {
            purchaseInformation = controller.enterItemInfo(scannedItem);
            showOnScreen(purchaseInformation);
        }

        System.out.println("Method enterItemInfo OK\n\n");







        //if customer asks for discount
        controller.createTestDiscount(1337);

        int customerID = 1337;
        purchaseInformation = controller.requestDiscount(customerID);

        //empty method to illustrate information on the screen
        showOnScreen(purchaseInformation);

        System.out.println("Method requestDiscount OK\n\n");



        //enter payment

        double paidAmount = 130;
        ChangeDTO change = controller.enterPayment(paidAmount);
        showOnScreen(change);
        System.out.println("Method enterPayment OK\n\n");


        // end sale
        ReceiptDTO receipt = controller.endSale();

        System.out.println("Method endSale OK\n\n");


        //empty method to illustrate the printing
        printReceipt(receipt);


    }




    private void showOnScreen(PurchaseDTO info){
        //show information on screen.
        System.out.println("Information about the purchase:\n");
        for (SoldItem item : info.getRegisteredItems()) {
            System.out.print(item.getItem().getDescription());
            System.out.print(", "+ item.getItem().getPrice() +", ");
            System.out.print(item.getQuantity()+ "pc\n");
        }
        System.out.println("Running total: "+ info.getRunningTotal()+
                        "\nTotal VAT: "+ info.getTotalVAT());
    }

    public void showOnScreen(ChangeDTO change){
        System.out.println("Information about the change");
        System.out.println("change: "+ change.getAmount());

    }

    private void printReceipt(ReceiptDTO receipt){
        //prints the receipt
        System.out.println("The receipt gets printed...");
    }


}

