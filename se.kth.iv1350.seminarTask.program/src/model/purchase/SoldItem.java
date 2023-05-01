package model.purchase;

import integration.inventory.ItemDTO;

public class SoldItem {

    private final ItemDTO item;
    private int quantity;
    private double discount;


    public SoldItem(ItemDTO item){
        this.item = item;
        this.quantity = 0;
        this.discount = 0;
    }
    public SoldItem(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
        this.discount = 0;
    }

    public ItemDTO getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addToQuantity(int quantity) {
        this.quantity += quantity;
    }

    void setDiscount(double discount) {
        this.discount = discount;
    }

    double getDiscount() {
        return discount;
    }

    public void removeFromQuantity(int quantity){
        if (quantity > 0)
            this.quantity -= quantity;
        //else exception
    }

    public boolean isEqualTo(SoldItem itemToBeComapared) {
        if (this.item.getIdentifier() == itemToBeComapared.getItem().getIdentifier() )
            return true;
        else
            return false;
    }
}
