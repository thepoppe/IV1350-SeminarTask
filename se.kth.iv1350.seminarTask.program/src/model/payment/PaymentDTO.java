package model.payment;

public class PaymentDTO {
    private final double amountInReg;
    private final double chargedAmount;




    PaymentDTO(ReceiptDTO receipt, double amountInReg) {
        this.chargedAmount = receipt.getRunningTotal()+receipt.getTotalVAT();
        this.amountInReg = amountInReg;
    }

    public double getAmountInReg() {
        return amountInReg;
    }
}
