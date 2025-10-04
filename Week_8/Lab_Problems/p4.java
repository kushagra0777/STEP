abstract class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public abstract void calculateBonus();
}

interface Payable {
    void generatePaySlip();
}

class Manager extends Employee implements Payable {
    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    public void calculateBonus() {
        double bonus = salary * 0.20;
        System.out.println("Bonus for Manager: " + bonus);
    }

    @Override
    public void generatePaySlip() {
        System.out.println("Pay Slip -> Name: " + name + ", Department: " + department + ", Salary: " + salary);
    }
}

public class p4 {
    public static void main(String[] args) {
        Manager m = new Manager("Kushagra", 80000, "IT");
        m.generatePaySlip();
        m.calculateBonus();
    }
}
