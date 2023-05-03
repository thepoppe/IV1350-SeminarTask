package model.payment;

public class PaymentDTO {
    private final double amountInReg;
    private final double chargedAmount;


    /**
     * Constructor, creates an instance of the class PaymentDTO
     * @param receipt - a ReceiptDTO with information of the amount paid
     * @param amountInReg - the amount present after the payment in the register
     */
    PaymentDTO(ReceiptDTO receipt, double amountInReg) {
        this.chargedAmount = receipt.getRunningTotal();
        this.amountInReg = amountInReg;
    }


    /**
     * getter for the amount in the register after payment is made
     * @return - a double of the amount present in the register
     */
    public double getAmountInReg() {
        return amountInReg;
    }
}
