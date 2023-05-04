package integration.inventory;

/**
 * This class is the information entered by the cashier when registering items.
 */
public class EnteredItemInfoDTO {
    private int identifier;
    private int quantity;


    /**
     * Constructor, creates an instance of the class EnteredITemInfoDTO
     * @param identifier - the entered identifier
     * @param quantity - the entered quantity
     */
    public EnteredItemInfoDTO(int identifier, int quantity){
        this.identifier = identifier;
        this.quantity = quantity;
    }


    /**
     * getter for the provided identifier
     * @return - an integer representing the identifier
     */
    public int getIdentifier() {
        return identifier;
    }


    /**
     * getter for quantity wanted by customer
     * @return - an integer representing the quantity wanted
     */
    public int getQuantity() {
        return quantity;
    }
}

