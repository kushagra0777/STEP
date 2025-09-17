class Color {
    protected String name;

    public Color(String name) {
        this.name = name;
        System.out.println("Color constructor: " + name);
    }
}

class PrimaryColor extends Color {
    protected int intensity;

    public PrimaryColor(String name, int intensity) {
        super(name);
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor: Intensity = " + intensity);
    }
}

public class p4 extends PrimaryColor {
    private String shade;

    public p4(String name, int intensity, String shade) {
        super(name, intensity);
        this.shade = shade;
        System.out.println("RedColor constructor: Shade = " + shade);
    }

    public static void main(String[] args) {
        p4 red = new p4("Red", 90, "Crimson");
    }
}
