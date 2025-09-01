class p1 {
    private static int counter = 0;
    private static int totalAccounts = 0;
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    public p1(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
        totalAccounts++;
    }
    private static String generateAccountNumber() {
        counter++;
        return String.format("ACC%03d", counter);
    }
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }
    public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) balance -= amount;
    }
    public double checkBalance() {
        return balance;
    }
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber + ", Holder: " + accountHolderName + ", Balance: " + balance);
    }
    public static void main(String[] args) {
        p1[] accounts = new p1[3];
        accounts[0] = new p1("Alice", 1000);
        accounts[1] = new p1("Bob", 2000);
        accounts[2] = new p1("Charlie", 500);

        accounts[0].deposit(500);
        accounts[1].withdraw(1000);
        accounts[2].deposit(200);

        for (p1 acc : accounts) acc.displayAccountInfo();
        System.out.println("Total Accounts: " + p1.getTotalAccounts());
    }
}
