package model.purchase;

import integration.discounts.DiscountDTO;
import integration.inventory.EnteredItemInfoDTO;
import integration.inventory.ItemDTO;
import java.util.ArrayList;

/**
 * The class Purchase is a public interface within the model/purchase package. Purchase distributes
 * method calls to the correct class within the system. This is to ensure better encapsulation.
 */
public class Purchase {

    private final SaleLog saleLog;
    private PurchaseDTO purchaseInformation;


    /**
     * default constructor creates an instance of SaleLog
     */
    public Purchase(){
        this.saleLog = new SaleLog();
    }


    /**
     * Getter for the internal saleLog.
     * @return - returns a SaleLog containing information about the registered Items
     */
    SaleLog getSaleLog(){
        return this.saleLog;
    }


    /**
     * getter for the PurchaseDTO
     * @return - returns a PurchaseDTO
     */
    public PurchaseDTO getPurchaseDTO() {
        return this.purchaseInformation;
    }


    /**
     * addItemToPurchase is the public interface for adding an Item to the current purchase. The
     * method then gives information to the correct Classes within the model package.
     * @param itemToAdd - the item that is added collected from inventory System
     * @param quantityOfItem - the quantity of the item to be added
     */
    public void addItemToPurchase(ItemDTO itemToAdd, int quantityOfItem){
        saleLog.addItemToSaleLog(itemToAdd, quantityOfItem);
        updatePurchaseInformation();
    }


    /**
     * adds the collected Discount to the saleLog
     * @param discounts - a list of DiscountDTO collected from DiscountHandler
     */
    public void addDiscount(ArrayList<DiscountDTO> discounts) {
        saleLog.addDiscountToSaleLog(discounts);
        updatePurchaseInformation();
    }


    /**
     * private method that creates a new PurchaseDTO with the latest information
     */
    private void updatePurchaseInformation(){
        this.purchaseInformation = new PurchaseDTO(this.saleLog);
    }
}
