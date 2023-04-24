package model.payment;

class Register {
    private double amount;

    Register(double registerAmount) {
        this.amount = registerAmount;
    }

    void updateRegister(ChangeDTO change, double paidAmount) {
        //update amount based on change
        this.amount += (paidAmount - change.getAmount());

    }

    double getRegisterAmount() {
        return this.amount;
    }
}
