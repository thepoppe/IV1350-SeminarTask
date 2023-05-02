package model.purchase;

import integration.discounts.DiscountDTO;
import integration.inventory.ItemDTO;
import java.util.ArrayList;


public class Purchase {

    private final SaleLog saleLog;
    private PurchaseDTO purchaseInformation;


    /**
     * default constructor creates an instance of SaleLog
     */
    public Purchase(){
        this.saleLog = new SaleLog();
    }
    SaleLog getSaleLog(){
        return this.saleLog;
    }

    public PurchaseDTO getPurchaseDTO() {
        return this.purchaseInformation;
    }


    public void addItemToPurchase(ItemDTO item, int quantity){
        saleLog.addItemToSaleLog(item, quantity);
        updatePurchaseInformation();
    }


    public void addDiscount(ArrayList<DiscountDTO> discounts) {
        saleLog.addDiscountToSaleLog(discounts);
        updatePurchaseInformation();
    }
    private void updatePurchaseInformation(){
        this.purchaseInformation = new PurchaseDTO(this.saleLog);
    }
}
