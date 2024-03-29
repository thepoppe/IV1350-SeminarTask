package model.payment;

/**
 * Interface for classes to be observed
 */
public interface RevenueObserver {
    void updateTotalRevenue(double newIncome) throws PaymentException;
}
