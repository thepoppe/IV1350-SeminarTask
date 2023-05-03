package model.payment;

class Register {
    private double amount;

    /**
     * Constructor creates an instance of Register
     * @param registerAmount - the amount of change present in the register
     */
    Register(double registerAmount) {
        this.amount = registerAmount;
    }


    /**
     * updates the amount of change stored in the register
     * @param change - ChangeDTO containing information of how much to withdraw.
     * @param paidAmount - double of the amount that is added as payment.
     */
    void updateRegister(ChangeDTO change, double paidAmount) {
        this.amount += (paidAmount - change.getAmount());
    }


    /**
     * getter for the current amount present in the register
     * @return - returns a double of the amount present
     */
    double getRegisterAmount() {
        return this.amount;
    }
}
