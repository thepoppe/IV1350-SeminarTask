package integration.inventory;

public class EnteredItemInfoDTO {
    private int identfier;
    private int quantity;

    public EnteredItemInfoDTO(int identifier, int quantity){
        this.identfier = identifier;
        this.quantity = quantity;
    }

    public int getIdentifier() {
        return identfier;
    }

    public int getQuantity() {
        return quantity;
    }
}

