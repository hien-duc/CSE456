package vn.edu.eiu.lab4;

import vn.edu.eiu.lab4.data.BankTransferPayment;
import vn.edu.eiu.lab4.data.CardPayment;
import vn.edu.eiu.lab4.data.EwalletPayment;
import vn.edu.eiu.lab4.data.PaymentMethod;
import vn.edu.eiu.lab4.service.PaymentService;

/**
 * Presentation Layer - Main Class
 * Entry point that demonstrates the payment processing system
 * with Dependency Injection and Open-Closed Principle
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Payment Processing Demo ===\n");

        // Test different payment methods
        demonstrateCardPayment();
        System.out.println();

        demonstrateEwalletPayment();
        System.out.println();

        demonstrateBankTransferPayment();
        System.out.println();

        demonstrateValidation();
    }

    /**
     * Demonstrate Card Payment
     */
    private static void demonstrateCardPayment() {
        System.out.println("--- Card Payment Demo ---");

        // Instantiate payment method
        PaymentMethod cardPayment = new CardPayment("1234567890123456", "John Doe");

        // Inject into service layer
        PaymentService paymentService = new PaymentService(cardPayment);

        // Process payment
        double amount = 150.75;
        System.out.println("Transaction Fee: $" + String.format("%.2f", paymentService.calculateTransactionFee(amount)));
        String result = paymentService.processPayment(amount);
        System.out.println("Result: " + result);
    }

    /**
     * Demonstrate E-Wallet Payment
     */
    private static void demonstrateEwalletPayment() {
        System.out.println("--- E-Wallet Payment Demo ---");

        // Instantiate payment method
        PaymentMethod ewalletPayment = new EwalletPayment("user123@email.com", "PayPal");

        // Inject into service layer
        PaymentService paymentService = new PaymentService(ewalletPayment);

        // Process payment
        double amount = 89.99;
        System.out.println("Transaction Fee: $" + String.format("%.2f", paymentService.calculateTransactionFee(amount)));
        String result = paymentService.processPayment(amount);
        System.out.println("Result: " + result);
    }

    /**
     * Demonstrate Bank Transfer Payment
     */
    private static void demonstrateBankTransferPayment() {
        System.out.println("--- Bank Transfer Payment Demo ---");

        // Instantiate payment method
        PaymentMethod bankTransfer = new BankTransferPayment("9876543210", "Chase Bank");

        // Inject into service layer
        PaymentService paymentService = new PaymentService(bankTransfer);

        // Process payment
        double amount = 500.00;
        System.out.println("Transaction Fee: $" + String.format("%.2f", paymentService.calculateTransactionFee(amount)));
        String result = paymentService.processPayment(amount);
        System.out.println("Result: " + result);
    }

    /**
     * Demonstrate validation logic
     */
    private static void demonstrateValidation() {
        System.out.println("--- Validation Demo ---");

        PaymentMethod cardPayment = new CardPayment("1111222233334444", "Jane Smith");
        PaymentService paymentService = new PaymentService(cardPayment);

        // Test invalid amount
        System.out.println("Testing negative amount:");
        System.out.println("Result: " + paymentService.processPayment(-50.0));

        // Test amount exceeding limit
        System.out.println("\nTesting amount exceeding limit:");
        System.out.println("Result: " + paymentService.processPayment(15000.0));
    }
}
