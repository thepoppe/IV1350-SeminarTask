package startup;

import view.View;
import integration.inventory.InventoryHandler;
import integration.payment.AccountingSystem;
import integration.discounts.DiscountHandler;
import controller.Controller;


public class Main {
    public static void main(String[] args) {


        InventoryHandler inventoryHandler = new InventoryHandler();
        DiscountHandler discountHandler = new DiscountHandler();
        AccountingSystem accounting = new AccountingSystem();
        Controller controller = new Controller(inventoryHandler, discountHandler, accounting);

        new View(controller).testSale();


    }
}


