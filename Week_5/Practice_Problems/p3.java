public class p3 {
    private String accountNumber;
    private double balance;
    private int pin;
    private boolean isLocked;
    private int failedAttempts;

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    public p3(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.pin = 0;
        this.isLocked = false;
        this.failedAttempts = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) throw new IllegalStateException("Account is locked");
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    public void setPin(int oldPin, int newPin) {
        if (this.pin == oldPin) this.pin = newPin;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) return false;
        if (this.pin == enteredPin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }

    public void unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            isLocked = false;
            resetFailedAttempts();
        }
    }

    public void deposit(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0) balance += amount;
        }
    }

    public void withdraw(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) balance -= amount;
        }
    }

    public void transfer(p3 target, double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && balance - amount >= MIN_BALANCE) {
                balance -= amount;
                target.balance += amount;
            }
        }
    }

    private void lockAccount() {
        isLocked = true;
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) lockAccount();
    }

    public static void main(String[] args) {
        p3 acc1 = new p3("A001", 1000);
        p3 acc2 = new p3("A002", 500);
        acc1.setPin(0, 1234);
        acc2.setPin(0, 5678);
        acc1.deposit(200, 1234);
        acc1.withdraw(100, 1234);
        acc1.transfer(acc2, 300, 1234);
        System.out.println(acc1.getBalance());
        System.out.println(acc2.getBalance());
    }
}
