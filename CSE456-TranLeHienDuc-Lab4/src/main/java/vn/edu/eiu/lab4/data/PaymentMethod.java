package vn.edu.eiu.lab4.data;

/**
 * Data Layer - Payment Method Interface
 * Defines the contract for all payment methods
 */
public interface PaymentMethod {
    /**
     * Abstract method to process payment
     * @param amount the amount to be paid
     * @return payment result message
     */
    String makePayment(double amount);
}
