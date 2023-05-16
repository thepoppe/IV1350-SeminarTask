package integration;

import integration.discounts.DiscountHandler;
import integration.inventory.InventoryHandler;
import integration.payment.AccountingHandler;

/**
 * This class creates the handlers in the integration layer
 */
public class ExternalHandlerCreator {

    private final InventoryHandler inventoryHandler;
    private final DiscountHandler discountHandler;
    private final AccountingHandler accountingHandler;

    /**
     * Constructor creates an instance of the class ExternalHandlerCreator and sets the attributes to new handlers
     */
    public ExternalHandlerCreator(){
        this.inventoryHandler = new InventoryHandler();
        this.discountHandler = new DiscountHandler();
        this.accountingHandler = new AccountingHandler();
    }

    /**
     * getter for InventoryHandler
     * @return returns an instance of the class InventoryHandler
     */
    public InventoryHandler getInventoryHandler() {
        return inventoryHandler;
    }

    /**
     * getter for DiscountHandler
     * @return returns an instance of the class DiscountHandler
     */
    public DiscountHandler getDiscountHandler() {
        return discountHandler;
    }

    /**
     * getter for AccountingHandler
     * @return returns an instance of the class AccountingHandler
     */
    public AccountingHandler getAccountingHandler() {
        return accountingHandler;
    }
}
