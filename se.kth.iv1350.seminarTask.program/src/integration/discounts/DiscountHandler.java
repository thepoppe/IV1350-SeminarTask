package integration.discounts;

import model.purchase.PurchaseDTO;

import java.util.ArrayList;

public class DiscountHandler {


    public DiscountHandler(){

    }


    public ArrayList<DiscountDTO> fetchAvailableDiscounts(int customerID, PurchaseDTO purchaseInformation){
        // Send identifier to database, collect discount
        ArrayList<DiscountDTO> discounts = new ArrayList<>();
        discounts.add(new DiscountDTO()); // Constructor not done. need identifier and price
        
        return discounts;
    }
}
