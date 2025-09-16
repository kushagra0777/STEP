import java.time.LocalDate;
import java.util.*;

final class MedicalRecord {
    private final String recordId;
    private final String patientDNA;
    private final String[] allergies;
    private final String[] medicalHistory;
    private final LocalDate birthDate;
    private final String bloodType;

    public MedicalRecord(String recordId, String patientDNA, String[] allergies,
                         String[] medicalHistory, LocalDate birthDate, String bloodType) {
        if (recordId == null || recordId.isBlank()) throw new IllegalArgumentException("Invalid Record ID");
        if (patientDNA == null || patientDNA.isBlank()) throw new IllegalArgumentException("DNA required");
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("Invalid birth date");
        if (bloodType == null || bloodType.isBlank()) throw new IllegalArgumentException("Blood type required");
        this.recordId = recordId;
        this.patientDNA = patientDNA;
        this.allergies = (allergies != null) ? allergies.clone() : new String[0];
        this.medicalHistory = (medicalHistory != null) ? medicalHistory.clone() : new String[0];
        this.birthDate = birthDate;
        this.bloodType = bloodType;
    }

    public String getRecordId() { return recordId; }
    public String getPatientDNA() { return patientDNA; }
    public String[] getAllergies() { return allergies.clone(); }
    public String[] getMedicalHistory() { return medicalHistory.clone(); }
    public LocalDate getBirthDate() { return birthDate; }
    public String getBloodType() { return bloodType; }
    public final boolean isAllergicTo(String substance) {
        for (String a : allergies) if (a.equalsIgnoreCase(substance)) return true;
        return false;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", birthDate=" + birthDate +
                ", bloodType='" + bloodType + '\'' +
                ", allergies=" + Arrays.toString(allergies) +
                ", history=" + Arrays.toString(medicalHistory) +
                '}';
    }

    @Override
    public int hashCode() { return Objects.hash(recordId, patientDNA); }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicalRecord)) return false;
        MedicalRecord that = (MedicalRecord) o;
        return recordId.equals(that.recordId) && patientDNA.equals(that.patientDNA);
    }
}

class Patient {
    private final String patientId;
    private final MedicalRecord medicalRecord;
    private String currentName;
    private String emergencyContact;
    private String insuranceInfo;
    private int roomNumber;
    private String attendingPhysician;

    public Patient(String name) { this("TEMP-" + UUID.randomUUID(), null, name, null, null, -1, null); }

    public Patient(String patientId, MedicalRecord record, String name,
                   String emergencyContact, String insuranceInfo,
                   int roomNumber, String attendingPhysician) {
        if (patientId == null || patientId.isBlank()) throw new IllegalArgumentException("Patient ID required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        this.patientId = patientId;
        this.medicalRecord = record;
        this.currentName = name;
        this.emergencyContact = emergencyContact;
        this.insuranceInfo = insuranceInfo;
        this.roomNumber = roomNumber;
        this.attendingPhysician = attendingPhysician;
    }

    public Patient(MedicalRecord record, String name, int roomNumber) {
        this("TRANSFER-" + UUID.randomUUID(), record, name, null, null, roomNumber, null);
    }

    public String getPatientId() { return patientId; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public String getCurrentName() { return currentName; }
    public void setCurrentName(String currentName) { if (currentName != null) this.currentName = currentName; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public String getInsuranceInfo() { return insuranceInfo; }
    public void setInsuranceInfo(String insuranceInfo) { this.insuranceInfo = insuranceInfo; }
    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public String getAttendingPhysician() { return attendingPhysician; }
    public void setAttendingPhysician(String attendingPhysician) { this.attendingPhysician = attendingPhysician; }
    String getBasicInfo() { return "Patient[" + patientId + "] Name: " + currentName; }
    public String getPublicInfo() { return "Patient " + currentName + " (Room " + roomNumber + ")"; }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", name='" + currentName + '\'' +
                ", physician='" + attendingPhysician + '\'' +
                ", room=" + roomNumber +
                '}';
    }
}

class Doctor {
    private final String licenseNumber;
    private final String specialty;
    private final Set<String> certifications;

    public Doctor(String licenseNumber, String specialty, Set<String> certifications) {
        this.licenseNumber = licenseNumber;
        this.specialty = specialty;
        this.certifications = new HashSet<>(certifications);
    }

    public String getLicenseNumber() { return licenseNumber; }
    public String getSpecialty() { return specialty; }
    public Set<String> getCertifications() { return new HashSet<>(certifications); }
}

class Nurse {
    private final String nurseId;
    private final String shift;
    private final List<String> qualifications;

    public Nurse(String nurseId, String shift, List<String> qualifications) {
        this.nurseId = nurseId;
        this.shift = shift;
        this.qualifications = new ArrayList<>(qualifications);
    }

    public String getNurseId() { return nurseId; }
    public String getShift() { return shift; }
    public List<String> getQualifications() { return new ArrayList<>(qualifications); }
}

class Administrator {
    private final String adminId;
    private final List<String> accessPermissions;

    public Administrator(String adminId, List<String> accessPermissions) {
        this.adminId = adminId;
        this.accessPermissions = new ArrayList<>(accessPermissions);
    }

    public String getAdminId() { return adminId; }
    public List<String> getAccessPermissions() { return new ArrayList<>(accessPermissions); }
}

class HospitalSystem {
    private final Map<String, Object> patientRegistry = new HashMap<>();
    public static final String POLICY_PRIVACY = "HIPAA-COMPLIANT";
    public static final int MAX_PATIENTS = 1000;

    public boolean admitPatient(Object patient, Object staff) {
        if (!(patient instanceof Patient)) return false;
        if (!validateStaffAccess(staff, patient)) return false;
        Patient p = (Patient) patient;
        patientRegistry.put(p.getPatientId(), p);
        return true;
    }

    private boolean validateStaffAccess(Object staff, Object patient) {
        if (staff instanceof Doctor) return true;
        if (staff instanceof Nurse) return ((Patient) patient).getRoomNumber() >= 0;
        if (staff instanceof Administrator) return true;
        return false;
    }

    String internalAuditReport() { return "Audit: " + patientRegistry.size() + " patients registered."; }
}

public class p1 {
    public static void main(String[] args) {
        MedicalRecord rec = new MedicalRecord("R1", "DNA123",
                new String[]{"Peanuts"}, new String[]{"Asthma"},
                LocalDate.of(1995, 5, 20), "O+");

        Patient p = new Patient("P1", rec, "Alice", "Bob", "XYZ Insurance", 101, "Dr. Strange");
        Doctor d = new Doctor("LIC123", "Cardiology", Set.of("Surgery", "Research"));

        HospitalSystem hs = new HospitalSystem();
        System.out.println("Admit status: " + hs.admitPatient(p, d));
        System.out.println(p.getPublicInfo());
        System.out.println(hs.internalAuditReport());
    }
}
