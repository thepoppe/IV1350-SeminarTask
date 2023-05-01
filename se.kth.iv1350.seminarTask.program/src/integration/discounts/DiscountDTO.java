package integration.discounts;

public class DiscountDTO {

    private int itemIdentifier;
    private double discount;

     DiscountDTO(int itemIdentifier, double recucedPrice){
        this.itemIdentifier = itemIdentifier;
        this.discount = recucedPrice;
     }

    public double getDiscount() {
        return discount;
    }

    public int getItemIdentifier() {
        return itemIdentifier;
    }
}
