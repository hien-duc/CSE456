package vn.edu.eiu.lab4.data;

/**
 * E-Wallet Payment Implementation
 */
public class EwalletPayment implements PaymentMethod {
    private String walletId;
    private String walletProvider;

    public EwalletPayment(String walletId, String walletProvider) {
        this.walletId = walletId;
        this.walletProvider = walletProvider;
    }

    @Override
    public String makePayment(double amount) {
        return String.format("Processing e-wallet payment of $%.2f via %s (ID: %s)",
                           amount, walletProvider, walletId);
    }
}
