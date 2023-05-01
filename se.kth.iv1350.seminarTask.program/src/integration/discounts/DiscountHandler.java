package integration.discounts;

import model.purchase.PurchaseDTO;

import java.util.ArrayList;

public class DiscountHandler {

    private ArrayList<Integer> eligableCustomers;
    private ArrayList<DiscountDTO> availableDiscounts;

    public DiscountHandler(){
    }


    public ArrayList<DiscountDTO> fetchAvailableDiscounts(int customerID, PurchaseDTO purchaseInformation){
        // Send identifier to database, collect discount
        ArrayList<DiscountDTO> discounts = new ArrayList<>();
        if (eligableCustomers.contains(customerID))
            discounts = availableDiscounts;
        
        return discounts;
    }

    public void createTestDiscount(int i) {
        eligableCustomers = new ArrayList<Integer>();
        eligableCustomers.add(i);
        availableDiscounts = new ArrayList<DiscountDTO>();
        availableDiscounts.add(new DiscountDTO(8, 2.0));

    }
}
