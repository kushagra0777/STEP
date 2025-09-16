import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class p4 implements Serializable {
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;

    public p4() {}

    public p4(String employeeId, String firstName, String lastName,
              double salary, String department, Date hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { if (salary >= 0) this.salary = salary; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getFullName() { return firstName + " " + lastName; }
    public int getYearsOfService() {
        long diff = new Date().getTime() - hireDate.getTime();
        return (int) (diff / (1000L * 60 * 60 * 24 * 365));
    }
    public String getFormattedSalary() { return String.format("$%.2f", salary); }

    public void setFullName(String fullName) {
        String[] parts = fullName.split(" ", 2);
        if (parts.length == 2) {
            this.firstName = parts[0];
            this.lastName = parts[1];
        }
    }

    @Override
    public String toString() {
        return employeeId + " - " + getFullName() + " - " + department +
               " - " + getFormattedSalary() + " - Active: " + isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof p4)) return false;
        p4 that = (p4) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    public static void main(String[] args) {
        p4 e1 = new p4("E001", "John", "Doe", 50000, "IT", new Date(), true);
        p4 e2 = new p4();
        e2.setEmployeeId("E002");
        e2.setFullName("Jane Smith");
        e2.setSalary(60000);
        e2.setDepartment("HR");
        e2.setHireDate(new Date());
        e2.setActive(true);
        System.out.println(e1);
        System.out.println(e2.getFullName());
        System.out.println(e2.getFormattedSalary());
    }
}
