package model.purchase;

import integration.inventory.ItemDTO;

public class ItemWithQuantity {

    private final ItemDTO item;
    private int quantity;
    private double discount;


    /**
     * Creates an instance of ItemWithQuantity
     * @param item - the item information
     */
    public ItemWithQuantity(ItemDTO item){
        this.item = item;
        this.quantity = 0;
        this.discount = 0;
    }

    /**
     * Creates an instance of a ItemWithQuantity with a specific quantity
     * @param item - the item information
     * @param quantity - the quantity of items registered
     */
    public ItemWithQuantity(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
        this.discount = 0;
    }

    /**
     * getter for item Information
     * @return - returns a ItemDTO containing unchangeable information about the item
     */
    public ItemDTO getItem() {
        return item;
    }


    /**
     * getter for the current quantity
     * @return - returns an integer representing the quantity
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * updates the quantity of the selected Item
     * @param quantityToAdd - is the amount of entities to be added to the quantity parameter
     */
    public void addToQuantity(int quantityToAdd) {
        this.quantity += quantityToAdd;
    }


    /**
     * updates the discount amount for the item
     * @param discountToAdd - is the amount to add to the discount
     */
    void setDiscount(double discountToAdd) {
        this.discount = discountToAdd;
    }

    /**
     * getter for discount
     * @return - returns a double of the amount of discount for the item
     */
    double getDiscount() {
        return discount;
    }


    /**
     * removeFromQuantity is an added method to update the hardcoded stock inventory.
     * @param quantityToRemove -  is the quantity to withdraw from the current quantity
     */
    public void removeFromQuantity(int quantityToRemove){
        if (this.quantity > 0)
            this.quantity -= quantityToRemove;
        //else exception
    }


    /**
     * Compares the identifier of two instances of the class ItemWithQuantity
     * @param itemToBeCompared - the item that is compared to this item.
     * @return - returns a boolean.
     */
    public boolean isEqualTo(ItemWithQuantity itemToBeCompared) {
        return this.item.getIdentifier() == itemToBeCompared.getItem().getIdentifier();
    }
}
