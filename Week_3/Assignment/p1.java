
class p1 {
    private static int seq = 0;
    private static int totalAccounts = 0;
    private static String bankName = "Unset";
    private final String accountHolderName;
    private final String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    public p1(String accountHolderName, double openingDeposit) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = Math.max(0, openingDeposit);
        this.totalIncome = Math.max(0, openingDeposit);
        totalAccounts++;
    }

    public static void setBankName(String name) { bankName = name; }
    public static int getTotalAccounts() { return totalAccounts; }
    public static String generateAccountNumber() { seq++; return String.format("ACC%03d", seq); }

    public void addIncome(double amount, String description) {
        if (amount > 0) { currentBalance += amount; totalIncome += amount; }
    }

    public void addExpense(double amount, String description) {
        if (amount > 0 && currentBalance >= amount) { currentBalance -= amount; totalExpenses += amount; }
    }

    public double calculateSavings() { return totalIncome - totalExpenses; }

    public void displayAccountSummary() {
        System.out.println("Bank: " + bankName + " | Holder: " + accountHolderName + " | Acc#: " + accountNumber +
                " | Balance: " + currentBalance + " | Income: " + totalIncome + " | Expenses: " + totalExpenses +
                " | Savings: " + calculateSavings());
    }

    public static void main(String[] args) {
        p1.setBankName("Nova Bank");
        p1 a1 = new p1("Alice", 1000);
        p1 a2 = new p1("Bob", 500);
        p1 a3 = new p1("Charlie", 0);
        a1.addIncome(750, "Salary");
        a1.addExpense(300, "Groceries");
        a2.addIncome(1200, "Salary");
        a2.addExpense(700, "Rent");
        a3.addIncome(400, "Freelance");
        a3.addExpense(150, "Utilities");
        a1.displayAccountSummary();
        a2.displayAccountSummary();
        a3.displayAccountSummary();
        System.out.println("Bank Name (static, shared): " + p1.bankName);
        System.out.println("Total Accounts: " + p1.getTotalAccounts());
    }
}
