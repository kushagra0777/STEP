class p3 {
    private static int empCounter = 0;
    private static int totalEmployees = 0;
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;

    public p3(String empName, String department, double baseSalary, String empType) {
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = empType;
        this.empId = generateEmpId();
        totalEmployees++;
    }

    private static String generateEmpId() {
        empCounter++;
        return String.format("E%03d", empCounter);
    }

    public double calculateSalary(double bonus) {
        return baseSalary + bonus;
    }

    public double calculateSalary(int hours, double hourlyRate) {
        return hours * hourlyRate;
    }

    public double calculateSalary() {
        return baseSalary;
    }

    public double calculateTax(double salary, double rate) {
        return salary * rate / 100;
    }

    public void generatePaySlip(double salary, double tax) {
        System.out.println("EmpID: " + empId + ", Name: " + empName + ", Dept: " + department + ", Type: " + empType + ", Salary: " + salary + ", Tax: " + tax);
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }

    public static void main(String[] args) {
        p3 e1 = new p3("Alice", "IT", 50000, "Full-Time");
        p3 e2 = new p3("Bob", "HR", 0, "Part-Time");
        p3 e3 = new p3("Charlie", "Finance", 40000, "Contract");

        double salary1 = e1.calculateSalary(10000);
        double tax1 = e1.calculateTax(salary1, 10);
        e1.generatePaySlip(salary1, tax1);

        double salary2 = e2.calculateSalary(80, 200);
        double tax2 = e2.calculateTax(salary2, 5);
        e2.generatePaySlip(salary2, tax2);

        double salary3 = e3.calculateSalary();
        double tax3 = e3.calculateTax(salary3, 8);
        e3.generatePaySlip(salary3, tax3);

        System.out.println("Total Employees: " + p3.getTotalEmployees());
    }
}
