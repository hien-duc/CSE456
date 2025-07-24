package vn.edu.eiu.lab4.data;

/**
 * Credit/Debit Card Payment Implementation
 */
public class CardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;

    public CardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public String makePayment(double amount) {
        return String.format("Processing card payment of $%.2f for %s using card ****%s",
                           amount, cardHolderName, cardNumber.substring(cardNumber.length() - 4));
    }
}
