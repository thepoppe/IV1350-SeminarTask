package view;

public class EnteredItemInfoDTO {
    private int identfier;
    private int quantity;

    public EnteredItemInfoDTO(int identifier, int quantity){
        this.identfier = identifier;
        this.quantity = quantity;
    }

    public int getIdentfier() {
        return identfier;
    }

    public int getQuantity() {
        return quantity;
    }
}
