class Course {
    String title, instructor, date;
    Course(String t, String i, String d) { title = t; instructor = i; date = d; }
    void showProgress() { System.out.println(title + " by " + instructor + " (" + date + ")"); }
}
class VideoCourse extends Course {
    VideoCourse(String t, String i, String d) { super(t,i,d); }
    void showProgress() { System.out.println(title + ": Video progress 70%, Watch time 10 hrs"); }
}
class InteractiveCourse extends Course {
    InteractiveCourse(String t, String i, String d) { super(t,i,d); }
    void showProgress() { System.out.println(title + ": Interactive progress, Quizzes 5/6, Projects 2/3"); }
}
class ReadingCourse extends Course {
    ReadingCourse(String t, String i, String d) { super(t,i,d); }
    void showProgress() { System.out.println(title + ": Reading progress 120/200 pages, Notes updated"); }
}
class CertificationCourse extends Course {
    CertificationCourse(String t, String i, String d) { super(t,i,d); }
    void showProgress() { System.out.println(title + ": Certification Exam attempted 1, Status: Pending"); }
}
public class p2 {
    public static void main(String[] args) {
        Course c1 = new VideoCourse("Java Basics", "Alice", "Jan 2025");
        Course c2 = new InteractiveCourse("AI Bootcamp", "Bob", "Feb 2025");
        Course c3 = new ReadingCourse("History of CS", "Charlie", "Mar 2025");
        Course c4 = new CertificationCourse("AWS Cloud", "David", "Apr 2025");
        c1.showProgress();
        c2.showProgress();
        c3.showProgress();
        c4.showProgress();
    }
}
