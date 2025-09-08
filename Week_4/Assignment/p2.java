import java.util.*;

class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    public BankAccount() {
        this("Unknown", 0, 0.0);
    }

    public BankAccount(String accountHolder) {
        this(accountHolder, new Random().nextInt(10000), 0.0);
    }

    public BankAccount(String accountHolder, double balance) {
        this(accountHolder, new Random().nextInt(10000), balance);
    }

    public BankAccount(String accountHolder, int accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) balance -= amount;
        else System.out.println("Insufficient balance");
    }

    public void displayAccount() {
        System.out.println("Account Holder: " + accountHolder + ", Account No: " + accountNumber + ", Balance: " + balance);
    }
}

public class p2 {
    public static void main(String[] args) {
        BankAccount a1 = new BankAccount();
        BankAccount a2 = new BankAccount("Alice");
        BankAccount a3 = new BankAccount("Bob", 1000.0);
        a2.deposit(500);
        a3.withdraw(200);
        a1.displayAccount();
        a2.displayAccount();
        a3.displayAccount();
    }
}
