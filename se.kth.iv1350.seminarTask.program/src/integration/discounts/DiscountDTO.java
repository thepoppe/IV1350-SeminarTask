package integration.discounts;

public class DiscountDTO {

    private int itemIdentifier;
    private double discount;

     public DiscountDTO(int itemIdentifier, double reducedPrice){
        this.itemIdentifier = itemIdentifier;
        this.discount = reducedPrice;
     }

    public double getDiscount() {
        return discount;
    }

    public int getItemIdentifier() {
        return itemIdentifier;
    }
}
