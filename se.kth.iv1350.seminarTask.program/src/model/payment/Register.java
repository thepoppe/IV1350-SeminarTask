package model.payment;

/**
 * Register represent the cash registry and contains the available change. The Register is also
 * responsible for calculating the change to be returned to customer.
 */
class Register {
    private double amount;

    /**
     * Constructor creates an instance of Register
     * @param registerAmount the amount of change present in the register
     */
    Register(double registerAmount) {
        this.amount = registerAmount;
    }


    /**
     * updates the amount of change stored in the register if a payment is successful
     * @param change ChangeDTO containing information of how much to withdraw.
     * @param paidAmount double of the amount that is added as payment.
     * @throws PaymentException is thrown if the requested amount of change is larger than available
     */
    void updateRegister(ChangeDTO change, double paidAmount) throws PaymentException {
        if (change.getAmount() > this.amount){
            throw new PaymentException("Not enough change in the register\n");
        }
        this.amount += (paidAmount - change.getAmount());
    }


    /**
     * getter for the current amount present in the register
     * @return returns a double of the amount present
     */
    double getRegisterAmount() {
        return this.amount;
    }
}
