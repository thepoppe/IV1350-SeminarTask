package integration.discounts;

import model.purchase.PurchaseDTO;

import java.util.ArrayList;

public class DiscountHandler {

    private ArrayList<Integer> eligibleCustomers;
    private ArrayList<DiscountDTO> availableDiscounts;

    public DiscountHandler(){
    }


    public ArrayList<DiscountDTO> fetchAvailableDiscounts(int customerID, PurchaseDTO purchaseInformation){
        // Send identifier to database, collect discount
        ArrayList<DiscountDTO> discounts = new ArrayList<>();
        if (eligibleCustomers.contains(customerID))
            discounts = availableDiscounts;
        
        return discounts;
    }

    public void createTestDiscount(int i) {
        eligibleCustomers = new ArrayList<>();
        eligibleCustomers.add(i);
        availableDiscounts = new ArrayList<>();
        availableDiscounts.add(new DiscountDTO(8, 2.0));

    }
}
