package integration.payment;

import model.payment.PaymentDTO;

/**
 * AccountingHandler is a part of the integration layer and is responsible for storing the paymentInformation
 * to external database. AccountingHandler also keeps track of the amount in the register.
 */
public class AccountingHandler {

    private double registerAmount;

    /**
     * Constructor, creates an instance of AccountingHandler with the register amount set to 1000 for testing
     */
    public AccountingHandler(){
        this.registerAmount = 1000;
    }

    /**
     * getter for the amount in the register
     * @return - a double of the amount of change in the register
     */
    public double getRegisterAmount(){
        return registerAmount;
    }

    /**
     * Contacts the External Database and register the accounting details.
     * This body is empty since the task is to dode the model not the data layers.
     * Method also updates the amount in the register.
     * @param paymentInfo - PaymentDTO with information about the Payment.
     */
    public void registerPayment(PaymentDTO paymentInfo) {
        // Updates database with information about the purchase.
        this.registerAmount = paymentInfo.getAmountInReg();
    }
}
