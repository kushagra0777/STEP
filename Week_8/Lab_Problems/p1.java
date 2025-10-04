abstract class Fruit {
    protected String color;
    protected String taste;

    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    public abstract void showDetails();
}

interface Edible {
    void nutrientsInfo();
}

class Apple extends Fruit implements Edible {
    private String variety;

    public Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    @Override
    public void showDetails() {
        System.out.println("Apple Variety: " + variety + ", Color: " + color + ", Taste: " + taste);
    }

    @Override
    public void nutrientsInfo() {
        System.out.println("Rich in Fiber, Vitamin C, and Antioxidants.");
    }
}

public class p1 {
    public static void main(String[] args) {
        Apple a = new Apple("Red", "Sweet", "Kashmiri");
        a.showDetails();
        a.nutrientsInfo();
    }
}
