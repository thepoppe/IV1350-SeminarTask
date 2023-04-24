package integration.payment;

import model.payment.PaymentDTO;

public class AccountingSystem {

    private double registerAmount;
    public AccountingSystem(){

    }

    public double getRegisterAmount(){
        return registerAmount;
    }

    public void registerPayment(PaymentDTO paymentInfo) {

        // Updates database with information about the purchase.

        //updates the amount in register
        this.registerAmount = paymentInfo.getAmountInReg();
    }
}
