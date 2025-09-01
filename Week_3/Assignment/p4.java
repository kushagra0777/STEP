import java.util.*;

class Subject {
    final String subjectCode;
    final String subjectName;
    final int credits;
    final String instructor;
    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode; this.subjectName = subjectName; this.credits = credits; this.instructor = instructor;
    }
}

class p4 {
    private static int seq=0;
    private static int totalStudents=0;
    private static String schoolName="Unset";
    private static String[] gradingScale = {"A","B","C","D","F"};
    private static double passPercentage = 40.0;

    final String studentId;
    final String studentName;
    final String className;
    final String[] subjects;
    final double[][] marks;
    double gpa;

    public p4(String studentName, String className, String[] subjects) {
        this.studentId=String.format("S%03d", ++seq);
        this.studentName=studentName;
        this.className=className;
        this.subjects=subjects;
        this.marks=new double[subjects.length][5];
        this.gpa=0;
        totalStudents++;
    }

    public static void setGradingScale(String[] scale, double passPct){ gradingScale=scale; passPercentage=passPct; }
    public static void setSchoolName(String name){ schoolName=name; }
    public static int getTotalStudents(){ return totalStudents; }

    public void addMarks(String subject, double value) {
        int idx = -1;
        for (int i=0;i<subjects.length;i++) if (subjects[i].equalsIgnoreCase(subject)) { idx=i; break; }
        if (idx==-1) return;
        for (int t=0;t<marks[idx].length;t++) if (marks[idx][t]==0){ marks[idx][t]=value; break; }
    }

    public void calculateGPA() {
        double total=0; int cnt=0;
        for (int i=0;i<subjects.length;i++)
            for (int t=0;t<marks[i].length;t++)
                if (marks[i][t]>0){ total+=marks[i][t]; cnt++; }
        double avg = cnt==0?0:total/cnt;
        if (avg>=90) gpa=4.0; else if (avg>=80) gpa=3.0; else if (avg>=70) gpa=2.0; else if (avg>=60) gpa=1.0; else gpa=0.0;
    }

    public boolean checkPromotionEligibility() {
        double total=0; int cnt=0;
        for (int i=0;i<subjects.length;i++)
            for (int t=0;t<marks[i].length;t++)
                if (marks[i][t]>0){ total+=marks[i][t]; cnt++; }
        double pct = cnt==0?0:total/cnt;
        return pct>=passPercentage;
    }

    public void generateReportCard() {
        calculateGPA();
        System.out.println("School: "+schoolName+" | "+studentName+" ("+studentId+") | Class: "+className+" | GPA: "+gpa+" | Promote: "+checkPromotionEligibility());
    }

    public static double calculateClassAverage(p4[] students) {
        double total=0; int cnt=0;
        for (p4 s: students) if (s!=null){
            for (int i=0;i<s.subjects.length;i++)
                for (int t=0;t<s.marks[i].length;t++)
                    if (s.marks[i][t]>0){ total+=s.marks[i][t]; cnt++; }
        }
        return cnt==0?0:total/cnt;
    }

    public static p4[] getTopPerformers(p4[] students, int count) {
        p4[] copy = Arrays.copyOf(students, students.length);
        for (p4 s: copy) if (s!=null) s.calculateGPA();
        Arrays.sort(copy, new Comparator<p4>() {
            public int compare(p4 a, p4 b){
                if (a==null && b==null) return 0; if (a==null) return 1; if (b==null) return -1;
                return Double.compare(b.gpa,a.gpa);
            }
        });
        return Arrays.copyOf(copy, Math.min(count, copy.length));
    }

    public static void generateSchoolReport(p4[] students) {
        System.out.println("Total Students: "+getTotalStudents()+" | Class Avg: "+calculateClassAverage(students));
        p4[] top = getTopPerformers(students, 3);
        System.out.println("Top Performers:");
        for (p4 s: top) if (s!=null) System.out.println(s.studentName+" - GPA "+s.gpa);
    }

    public static void main(String[] args) {
        setSchoolName("Greenwood High");
        setGradingScale(new String[]{"A","B","C","D","F"}, 40);

        String[] subs = {"Math","Science","English"};
        p4 a = new p4("Alice","10-A",subs);
        p4 b = new p4("Bob","10-A",subs);
        p4 c = new p4("Cara","10-A",subs);
        a.addMarks("Math",95); a.addMarks("Science",88); a.addMarks("English",91);
        b.addMarks("Math",72); b.addMarks("Science",65); b.addMarks("English",70);
        c.addMarks("Math",55); c.addMarks("Science",60); c.addMarks("English",58);

        p4[] cls = {a,b,c};
        for (p4 s: cls) s.generateReportCard();
        generateSchoolReport(cls);
    }
}
