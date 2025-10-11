// File: PaymentGateway.java
class Payment {
    public void pay() {
        System.out.println("Generic payment");
    }
}

class CreditCardPayment extends Payment {
    @Override public void pay() {
        System.out.println("Credit Card Payment processed");
    }
}

class WalletPayment extends Payment {
    @Override public void pay() {
        System.out.println("Wallet Payment processed");
    }
}

public class p3 {
    public static void main(String[] args) {
        Payment[] payments = { new CreditCardPayment(), new WalletPayment() };
        for (Payment p : payments) {
            System.out.println("Processing: " + p.getClass().getSimpleName());
            p.pay();
        }
    }
}
