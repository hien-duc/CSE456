package vn.edu.eiu.lab4.data;

/**
 * Bank Transfer Payment Implementation
 */
public class BankTransferPayment implements PaymentMethod {
    private String accountNumber;
    private String bankName;

    public BankTransferPayment(String accountNumber, String bankName) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }

    @Override
    public String makePayment(double amount) {
        return String.format("Processing bank transfer of $%.2f from %s account ****%s",
                           amount, bankName, accountNumber.substring(accountNumber.length() - 4));
    }
}
