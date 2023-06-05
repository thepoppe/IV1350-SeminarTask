package integration.discounts;

import model.purchase.PurchaseDTO;
import util.RandomWithComposition;
import util.RandomWithInheritance;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is a part of the integration layer and is responsible for retrieving information about discount
 * from the external discount system. Since this project does not have a database, two ArrayList represents
 * the database.
 */
public class DiscountHandler {
    private ArrayList<Integer> eligibleCustomers;
    private ArrayList<DiscountDTO> availableDiscounts;


    /**
     * Constructor creates an instance of the class DiscountHandler
     */
    public DiscountHandler(){
    }


    /**
     * collects available discount for the customer from the external database.
     * The database is simulated by an ArrayList
     * @param customerID - the identification of the customer
     * @param purchaseInformation - information about the purchase.
     * @return - returns a List of DiscountDTO that the customer is eligible for.
     */
    public ArrayList<DiscountDTO> fetchAvailableDiscounts(int customerID, PurchaseDTO purchaseInformation){

        ArrayList<DiscountDTO> discounts = new ArrayList<>();
        if (eligibleCustomers.contains(customerID))
            discounts = availableDiscounts;
        
        return discounts;
    }


    /**
     * Creates a test discount reducing the price for the item with identifier 9. This is made for testing
     * since external database is not provided
     * @param customerIdentification - the id of the eligible customer
     */
    public void createTestDiscount(int customerIdentification, int identifierForItem, double priceReduction) {
        eligibleCustomers = new ArrayList<>();
        eligibleCustomers.add(customerIdentification);
        availableDiscounts = new ArrayList<>();
        availableDiscounts.add(new DiscountDTO(identifierForItem, priceReduction));


        RandomWithComposition firstRandom = new RandomWithComposition();
        RandomWithInheritance secondRandom = new RandomWithInheritance();
        eligibleCustomers.add(firstRandom.nextInt(eligibleCustomers,1,3));
        eligibleCustomers.add(secondRandom.nextInt(eligibleCustomers,1,3));
        System.out.println("\nThe created test customerIDs:\n"+Arrays.toString(eligibleCustomers.toArray())+"\n\n");

    }
}
