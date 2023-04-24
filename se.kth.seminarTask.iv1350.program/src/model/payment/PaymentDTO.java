package model.payment;

public class PaymentDTO {
    private final double amountInReg;




    PaymentDTO(ReceiptDTO receipt, double amountInReg) {

        this.amountInReg = amountInReg;
    }

    public double getAmountInReg() {
        return amountInReg;
    }
}
