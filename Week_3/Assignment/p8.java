
class Patient {
    private static int pseq=0;
    static int totalPatients=0;

    final String patientId;
    final String patientName;
    final int age;
    final String gender;
    final String contactInfo;
    final String[] medicalHistory;
    final String[] currentTreatments;
    int mhc=0, tc=0;

    public Patient(String patientName,int age,String gender,String contactInfo){
        this.patientId=String.format("P%04d", ++pseq);
        this.patientName=patientName; this.age=age; this.gender=gender; this.contactInfo=contactInfo;
        this.medicalHistory=new String[50]; this.currentTreatments=new String[20];
        totalPatients++;
    }
    public void updateTreatment(String t){ if (tc<currentTreatments.length) currentTreatments[tc++]=t; }
    public void dischargePatient(){ tc=0; }
}

class Doctor {
    private static int dseq=0;
    final String doctorId;
    final String doctorName;
    final String specialization;
    final String[] availableSlots;
    int patientsHandled=0;
    double consultationFee;

    public Doctor(String doctorName,String specialization,double consultationFee,String[] slots){
        this.doctorId=String.format("D%04d", ++dseq);
        this.doctorName=doctorName; this.specialization=specialization; this.consultationFee=consultationFee; this.availableSlots=slots;
    }
}

class p8 {
    private static int aseq=0;
    static int totalAppointments=0;
    static String hospitalName="Unset";
    static double totalRevenue=0;

    final String appointmentId;
    final Patient patient;
    final Doctor doctor;
    final String appointmentDate;
    final String appointmentTime;
    String status;

    public p8(Patient p, Doctor d, String date, String time, String type){
        this.appointmentId=String.format("A%04d", ++aseq);
        this.patient=p; this.doctor=d; this.appointmentDate=date; this.appointmentTime=time; this.status=type;
        totalAppointments++; totalRevenue += billFor(type, d.consultationFee); d.patientsHandled++;
    }

    private double billFor(String type, double base){
        if ("Emergency".equalsIgnoreCase(type)) return base*2;
        if ("Follow-up".equalsIgnoreCase(type)) return base*0.5;
        return base;
    }

    public static p8 scheduleAppointment(Patient p, Doctor d, String date, String time, String type){
        return new p8(p,d,date,time,type);
    }

    public static void cancelAppointment(p8 a){ if (a!=null) a.status="Cancelled"; }

    public static double generateBill(p8 a){
        if (a==null) return 0;
        if ("Emergency".equalsIgnoreCase(a.status)) return a.doctor.consultationFee*2;
        if ("Follow-up".equalsIgnoreCase(a.status)) return a.doctor.consultationFee*0.5;
        return a.doctor.consultationFee;
    }

    public static void generateHospitalReport(Doctor[] docs, Patient[] pats){
        System.out.println("Hospital: "+hospitalName+" | Patients: "+Patient.totalPatients+" | Appointments: "+totalAppointments+" | Revenue: "+totalRevenue);
        for (Doctor d: docs) if (d!=null) System.out.println(d.doctorName+" | "+d.specialization+" | Patients: "+d.patientsHandled);
    }

    public static void getDoctorUtilization(Doctor[] docs){
        for (Doctor d: docs) if (d!=null) System.out.println(d.doctorName+" Slots: "+d.availableSlots.length+" | Patients: "+d.patientsHandled);
    }

    public static void getPatientStatistics(Patient[] pats){
        System.out.println("Total Patients: "+Patient.totalPatients);
    }

    public static void main(String[] args){
        hospitalName="Sunrise Hospital";
        Doctor d1=new Doctor("Dr. Alice","Cardiology",1000,new String[]{"10:00","11:00"});
        Doctor d2=new Doctor("Dr. Bob","Orthopedics",800,new String[]{"12:00","13:00","14:00"});
        Patient p1=new Patient("John Doe",35,"M","999-111");
        Patient p2=new Patient("Jane Roe",28,"F","999-222");

        p8 a1=scheduleAppointment(p1,d1,"2025-09-02","10:00","Consultation");
        p8 a2=scheduleAppointment(p2,d2,"2025-09-02","12:00","Emergency");
        p1.updateTreatment("ECG"); p2.updateTreatment("X-Ray");

        System.out.println("Bill A1: "+generateBill(a1));
        System.out.println("Bill A2: "+generateBill(a2));

        generateHospitalReport(new Doctor[]{d1,d2}, new Patient[]{p1,p2});
        getDoctorUtilization(new Doctor[]{d1,d2});
        getPatientStatistics(new Patient[]{p1,p2});
    }
}
