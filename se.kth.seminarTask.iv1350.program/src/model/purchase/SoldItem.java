package model.purchase;

import integration.inventory.ItemDTO;

public class SoldItem {

    private final ItemDTO item;
    private int quantity;


    SoldItem(ItemDTO item){
        this.item = item;
        this.quantity = 0;

    }

    public ItemDTO getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    void addQuantity(int quantity) {
        this.quantity += quantity;
    }

}
