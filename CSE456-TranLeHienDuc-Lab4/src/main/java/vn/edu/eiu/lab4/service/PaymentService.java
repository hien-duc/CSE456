package vn.edu.eiu.lab4.service;

import vn.edu.eiu.lab4.data.PaymentMethod;

/**
 * Service Layer - Business Logic
 *
 * Single Responsibility Principle (SRP) for this class:
 * This class has ONE responsibility: to process payments using the injected payment method.
 * It doesn't know HOW the payment is processed (that's the responsibility of PaymentMethod implementations),
 * it only orchestrates the payment process and handles business logic like validation,
 * logging, and transaction management.
 */
public class PaymentService {
    private PaymentMethod paymentMethod;

    /**
     * Constructor Dependency Injection
     * @param paymentMethod the payment method to be injected
     */
    public PaymentService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Process payment with business logic
     * @param amount the amount to process
     * @return processing result
     */
    public String processPayment(double amount) {
        // Business logic: validation
        if (amount <= 0) {
            return "Error: Payment amount must be greater than 0";
        }

        if (amount > 10000) {
            return "Error: Payment amount exceeds maximum limit of $10,000";
        }

        // Log the transaction (business logic)
        System.out.println("Starting payment processing...");

        // Delegate to the injected payment method
        String result = paymentMethod.makePayment(amount);

        // Log completion (business logic)
        System.out.println("Payment processing completed.");

        return result;
    }

    /**
     * Get transaction fee based on amount (business logic)
     * @param amount the payment amount
     * @return calculated fee
     */
    public double calculateTransactionFee(double amount) {
        return amount * 0.025; // 2.5% transaction fee
    }
}
