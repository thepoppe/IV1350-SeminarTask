package model.payment;

import model.purchase.PurchaseDTO;

public class Payment {


    private ChangeDTO change;
    private final Register register;

    private ReceiptDTO receipt;

    private PaymentDTO paymentInfo;


    public Payment(double registerAmount){
        this.register = new Register(registerAmount);

    }

    public void paymentForItems(double paidAmount, PurchaseDTO purchaseInformation) {

        this.change = new ChangeDTO(paidAmount, purchaseInformation);
        register.updateRegister(this.change, paidAmount);
        double amountInReg = register.getRegisterAmount();

        //Create receipt
        this.receipt = new ReceiptDTO();
        this.paymentInfo = new PaymentDTO(this.receipt, amountInReg);


    }

    public ChangeDTO getChange() {
        return change;
    }

    public PaymentDTO getPaymentInfo(){
        return paymentInfo;
    }

    public ReceiptDTO getReceipt() {
        return this.receipt;
    }
}
