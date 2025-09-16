public class p1 {
    private int privateField;
    String defaultField;
    protected double protectedField;
    public boolean publicField;

    private void privateMethod() {
        System.out.println("Private method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    public p1(int privateField, String defaultField,
                              double protectedField, boolean publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.protectedField = protectedField;
        this.publicField = publicField;
    }

    public void testInternalAccess() {
        System.out.println("privateField = " + privateField);
        System.out.println("defaultField = " + defaultField);
        System.out.println("protectedField = " + protectedField);
        System.out.println("publicField = " + publicField);
        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args) {
        p1 demo = new p1(10, "Hello", 20.5, true);
        System.out.println(demo.defaultField);
        System.out.println(demo.protectedField);
        System.out.println(demo.publicField);
        demo.defaultMethod();
        demo.protectedMethod();
        demo.publicMethod();
        demo.testInternalAccess();
    }
}

class SamePackageTest {
    public static void testAccess() {
        p1 demo = new p1(1, "Test", 2.5, false);
        System.out.println(demo.defaultField);
        System.out.println(demo.protectedField);
        System.out.println(demo.publicField);
        demo.defaultMethod();
        demo.protectedMethod();
        demo.publicMethod();
    }
}
