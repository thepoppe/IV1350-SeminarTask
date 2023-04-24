package view;

import controller.Controller;
import integration.inventory.ItemDTO;
import model.payment.ChangeDTO;
import model.purchase.PurchaseDTO;
import model.payment.ReceiptDTO;

public class View {


    private final Controller controller;

    public View(Controller c){
        this.controller = c;
    }



    // placeholder for methods
    public void testSale(){
        System.out.println("Program Starts...");

        controller.startSale();
        PurchaseDTO purchaseInformation;

        System.out.println("Method startSale OK");



        // create a list of items to test with
        ItemDTO[] items = {
                new ItemDTO(9,"Banana", 4.90, 0.25),
                new ItemDTO(9,"Banana", 4.90, 0.25),
                new ItemDTO(8,"Apple", 8.90,0.25),
                new ItemDTO(120,"Car stereo", 99.90, 0.40),
                new ItemDTO(9,"Banana", 4.90, 0.25)
        };


        // temporary loop to simulate the cashier going through the items
        for (int i = 0; i < items.length; i++) {
            ItemDTO itemToBeScanned = items[i];
            int currentIdentifier = itemToBeScanned.getIdentifier();
            int quantity = 1;
            for (int j = i+1; i < items.length ; i++) {
                if (currentIdentifier == items[i].getIdentifier())
                    quantity++;
                else break;
            }

            purchaseInformation = controller.enterItemInfo(
                    currentIdentifier, quantity);

            showOnScreen(purchaseInformation);
        }

        System.out.println("Method enterItemInfo OK");







        //if customer asks for discount
        int customerID = 007;
        purchaseInformation = controller.requestDiscount(customerID);

        //empty method to illustrate information on the screen
        showOnScreen(purchaseInformation);



        //enter payment

        double paidAmount = 0;
        ChangeDTO change = controller.enterPayment(paidAmount);


        // end sale
        ReceiptDTO receipt = controller.endSale();


        //empty method to illustrate the printing
        printReceipt(receipt);




    }




    //låtsasfunktion
    private void showOnScreen(PurchaseDTO info){
        //show information on screen.
        System.out.println("Information about the purchase");
    }

    private void printReceipt(ReceiptDTO receipt){
        //prints the receipt
        System.out.println("Prints Receipt");
    }


}
