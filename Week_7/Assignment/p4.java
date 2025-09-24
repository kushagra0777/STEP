class MedicalStaff {
    void duty() { System.out.println("Medical staff general duty scheduled"); }
}
class Doctor extends MedicalStaff {
    void duty() { System.out.println("Doctor: diagnose, prescribe, perform surgeries"); }
}
class Nurse extends MedicalStaff {
    void duty() { System.out.println("Nurse: administer medicine, monitor patients, assist procedures"); }
}
class Technician extends MedicalStaff {
    void duty() { System.out.println("Technician: operate equipment, run tests, maintain instruments"); }
}
class Administrator extends MedicalStaff {
    void duty() { System.out.println("Administrator: schedule appointments, manage records"); }
}
public class p4 {
    public static void main(String[] args) {
        MedicalStaff s1 = new Doctor();
        MedicalStaff s2 = new Nurse();
        MedicalStaff s3 = new Technician();
        MedicalStaff s4 = new Administrator();
        s1.duty();
        s2.duty();
        s3.duty();
        s4.duty();
    }
}
