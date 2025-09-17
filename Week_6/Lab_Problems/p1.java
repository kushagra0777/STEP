class Fruit {
    protected String color;
    protected String taste;

    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }
}

public class p1 extends Fruit {
    private String variety;

    public p1(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    public void displayInfo() {
        System.out.println("Apple Variety: " + variety);
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }

    public static void main(String[] args) {
        p1 a = new p1("Red", "Sweet", "Fuji");
        a.displayInfo();
    }
}
