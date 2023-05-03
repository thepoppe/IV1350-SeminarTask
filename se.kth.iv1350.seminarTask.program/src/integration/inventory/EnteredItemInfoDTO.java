package integration.inventory;

public class EnteredItemInfoDTO {
    private int identifier;
    private int quantity;

    public EnteredItemInfoDTO(int identifier, int quantity){
        this.identifier = identifier;
        this.quantity = quantity;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getQuantity() {
        return quantity;
    }
}

