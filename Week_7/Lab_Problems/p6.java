class SmartDevice {
    void control() { System.out.println("Generic smart device"); }
}
class SmartClassroom extends SmartDevice {
    void classroomControl() { System.out.println("Classroom: Lighting, AC, Projectors controlled"); }
}
class SmartLab extends SmartDevice {
    void labControl() { System.out.println("Lab: Equipment and Safety systems managed"); }
}
class SmartLibrary extends SmartDevice {
    void libraryControl() { System.out.println("Library: Occupancy and Book availability tracked"); }
}
public class p6 {
    public static void main(String[] args) {
        SmartDevice[] devices = { new SmartClassroom(), new SmartLab(), new SmartLibrary() };
        for (SmartDevice d : devices) {
            if (d instanceof SmartClassroom) ((SmartClassroom)d).classroomControl();
            else if (d instanceof SmartLab) ((SmartLab)d).labControl();
            else if (d instanceof SmartLibrary) ((SmartLibrary)d).libraryControl();
        }
    }
}
