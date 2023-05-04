package integration.inventory;


/**
 * ItemDTO represents the individual items for purchase. Contains general information about the item.
 */
public class ItemDTO {
    private final int identifier;
    private final String description;
    private final double price;
    private final double VAT;


    /**
     * Constructor for class ItemDTO
     *
     * @param identifier - the items identification number
     * @param description - the items description
     * @param price - the item's price
     * @param VAT - VATRate
     */
    public ItemDTO (int identifier, String description, double price, double VAT){
        this.identifier = identifier;
        this.description = description;
        this.price = price;
        this.VAT = VAT;
    }


    /**
     * Getter for identifier
     * @return identification number for the specific item
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * getter for description
     * @return item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter for price
     * @return price information
     */
    public double getPrice() {
        return price;
    }

    /**
     * getter for VAT rate
     * @return Vat for specific item
     */
    public double getVAT() {
        return VAT;
    }
}
