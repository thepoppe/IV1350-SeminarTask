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


    public void addItemToPurchase(ItemDTO item, int quantity){
        saleLog.addItemToSaleLog(item, quantity);
        this.purchaseInformation = new PurchaseDTO(this.saleLog);

    }


    public PurchaseDTO getPurchaseDTO() {
        return this.purchaseInformation;
    }

    public void addDiscount(ArrayList<DiscountDTO> discounts) {
        saleLog.addDiscountToSaleLog(discounts);
        this.purchaseInformation = new PurchaseDTO(saleLog);

    }
}
