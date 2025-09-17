class Light {
    String type;

    public Light() {
        this("Generic");
        System.out.println("Light default constructor");
    }

    public Light(String type) {
        this.type = type;
        System.out.println("Light constructor: " + type);
    }
}

public class p1 extends Light {
    String color;

    public p1() {
        this("LED", "White");
        System.out.println("LED default constructor");
    }

    public p1(String type, String color) {
        super(type);
        this.color = color;
        System.out.println("LED constructor: " + color);
    }

    public static void main(String[] args) {
        new p1();
        new p1("LED", "Blue");
    }
}
