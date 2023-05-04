package model.payment;

import model.purchase.PurchaseDTO;

/**
 * The class Payment is used as the public interface within the model/payment to handle the payment process
 */
public class Payment {

    private ChangeDTO change;
    private final Register register;
    private ReceiptDTO receipt;
    private PaymentDTO paymentInfo;


    /**
     * Constructor for the class Payment. Creates an instance of Payment.
     * @param registerAmount - the amount of change present in the Register
     */
    public Payment(double registerAmount){
        this.register = new Register(registerAmount);
    }


    /**
     * The public interface for handling payments. Sends the payment information to the correct class within
     * the model package.
     * @param paidAmount - the amount paid by the customer
     * @param purchaseInformation - a purchaseDTO containing all information about the purchase
     */
    public void paymentForItems(double paidAmount, PurchaseDTO purchaseInformation) {

        if (paidAmount >= purchaseInformation.getRunningTotal()) {
            this.change = new ChangeDTO(paidAmount, purchaseInformation);
            register.updateRegister(this.change, paidAmount);
            double amountInReg = register.getRegisterAmount();
            this.receipt = new ReceiptDTO(purchaseInformation, this.change);
            this.paymentInfo = new PaymentDTO(this.receipt, amountInReg);


        }


    }

    /**
     * getter for the change information
     * @return - changeDTO containing the information about the change
     */
    public ChangeDTO getChange() {
        return change;
    }

    /**
     * getter for PaymentInfo.
     * @return - PaymentDTO containing the information about the payment
     */
    public PaymentDTO getPaymentInfo(){
        return paymentInfo;
    }

    /**
     * getter for the receipt
     * @return - ReceiptDTO containing information for the printer to print out.
     */
    public ReceiptDTO getReceipt() {
        return this.receipt;
    }
}
