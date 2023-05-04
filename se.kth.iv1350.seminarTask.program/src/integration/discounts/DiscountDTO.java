package integration.discounts;

/**
 * DiscountDTO is one of the discounts applicable to the customers purchase. Consists of an item identifier and
 * the discount for that item
 */
public class DiscountDTO {

    private int itemIdentifier;
    private double discount;


    /**
     * Constructor creates an instance of the class DiscountDTO
     * @param itemIdentifier - integer representing the identifier for the item with a price reduction
     * @param reducedPrice - a double of the reduce price on the item
     */
     public DiscountDTO(int itemIdentifier, double reducedPrice){
        this.itemIdentifier = itemIdentifier;
        this.discount = reducedPrice;
     }


    /**
     * getter for  amount of discount on the item
     * @return - returns a double of the reduced price for the item.
     */
    public double getDiscount() {
        return discount;
    }


    /**
     * getter for identifier for the item
     * @return - returns an integer representing the identifier for the item
     */
    public int getItemIdentifier() {
        return itemIdentifier;
    }
}
