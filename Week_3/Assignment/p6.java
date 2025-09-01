
class Department {
    final String deptId;
    final String deptName;
    p6 manager;
    p6[] employees;
    double budget;
    int count=0;

    public Department(String deptId,String deptName,double budget,int capacity){
        this.deptId=deptId; this.deptName=deptName; this.budget=budget; this.employees=new p6[capacity];
    }
    public void addEmployee(p6 e){ if (count<employees.length){ employees[count++]=e; } }
}

class p6 {
    private static int seq=0;
    static int totalEmployees=0;
    static String companyName="Unset";
    static double totalSalaryExpense=0;
    static int workingDaysPerMonth=30;

    final String empId;
    final String empName;
    final String department;
    final String designation;
    final double baseSalary;
    final String joinDate;
    final boolean[] attendanceRecord; // 30 days
    String type;

    public p6(String empName,String department,String designation,double baseSalary,String joinDate,String type){
        this.empId=String.format("E%04d", ++seq);
        this.empName=empName; this.department=department; this.designation=designation; this.baseSalary=baseSalary;
        this.joinDate=joinDate; this.attendanceRecord=new boolean[30]; this.type=type;
        totalEmployees++;
    }

    public void markAttendance(int day, boolean present){ if (day>=1 && day<=30) attendanceRecord[day-1]=present; }

    public double calculateSalary(){
        int present=0; for (boolean p: attendanceRecord) if (p) present++;
        double prorated = baseSalary * present / workingDaysPerMonth;
        double bonus = calculateBonus();
        double salary = (type.equalsIgnoreCase("Part-time")) ? prorated : (type.equalsIgnoreCase("Contract")? baseSalary : baseSalary + bonus);
        totalSalaryExpense += salary;
        return salary;
    }

    public double calculateBonus(){
        int present=0; for (boolean p: attendanceRecord) if (p) present++;
        double perf = present>=26?0.10:(present>=22?0.05:0.0);
        return baseSalary * perf;
    }

    public void requestLeave(){}

    public String generatePaySlip(){
        double sal = calculateSalary();
        return empId+" | "+empName+" | "+designation+" | "+type+" | Salary: "+sal;
    }

    public static double calculateCompanyPayroll(p6[] emps){
        double sum=0; for (p6 e: emps) if (e!=null) sum+=e.calculateSalary(); return sum;
    }
    public static void getDepartmentWiseExpenses(Department[] deps){
        for (Department d: deps) if (d!=null){
            double sum=0; for (p6 e: d.employees) if (e!=null) sum+=e.calculateSalary();
            System.out.println(d.deptName+" -> "+sum);
        }
    }
    public static void getAttendanceReport(p6[] emps){
        for (p6 e: emps) if (e!=null){
            int present=0; for (boolean p: e.attendanceRecord) if (p) present++;
            System.out.println(e.empName+" Present: "+present);
        }
    }

    public static void main(String[] args){
        companyName="Helios Corp"; workingDaysPerMonth=30;

        p6 a=new p6("Alice","IT","Engineer",60000,"2024-06-01","Full-time");
        p6 b=new p6("Bob","IT","Intern",20000,"2025-01-01","Part-time");
        p6 c=new p6("Cara","Finance","Consultant",50000,"2025-03-01","Contract");

        for (int d=1; d<=30; d++){ a.markAttendance(d,true); b.markAttendance(d, d%2==0); c.markAttendance(d, d<=20); }

        Department it=new Department("D01","IT",1000000,10); it.manager=a; it.addEmployee(a); it.addEmployee(b);
        Department fin=new Department("D02","Finance",800000,10); fin.manager=c; fin.addEmployee(c);

        System.out.println(a.generatePaySlip());
        System.out.println(b.generatePaySlip());
        System.out.println(c.generatePaySlip());

        System.out.println("Company Payroll: "+calculateCompanyPayroll(new p6[]{a,b,c}));
        getDepartmentWiseExpenses(new Department[]{it,fin});
        getAttendanceReport(new p6[]{a,b,c});
        System.out.println("Total Employees: "+totalEmployees+" | Total Salary Expense: "+totalSalaryExpense);
    }
}
