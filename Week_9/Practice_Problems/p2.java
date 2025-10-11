import java.util.*;

class Employee {
    private String empCode;
    private String name;

    public Employee(String empCode, String name) {
        this.empCode = empCode;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee e = (Employee) o;
        return empCode.equals(e.empCode);
    }

    @Override
    public int hashCode() {
        return empCode.hashCode();
    }

    @Override
    public String toString() {
        return empCode + " - " + name;
    }
}

public class p2 {
    public static void main(String[] args) {
        Employee e1 = new Employee("BL001", "Ritika");
        Employee e2 = new Employee("BL001", "Ritika S.");

        System.out.println("== : " + (e1 == e2));
        System.out.println("equals() : " + e1.equals(e2));

        HashSet<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);
        System.out.println("HashSet size: " + set.size());
    }
}
