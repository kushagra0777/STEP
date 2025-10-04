abstract class Shape {
    protected double area;
    protected double perimeter;

    public abstract void calculateArea();
    public abstract void calculatePerimeter();
}

interface Drawable {
    void draw();
}

class Circle extends Shape implements Drawable {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea() {
        area = Math.PI * radius * radius;
        System.out.println("Area of Circle: " + area);
    }

    @Override
    public void calculatePerimeter() {
        perimeter = 2 * Math.PI * radius;
        System.out.println("Perimeter of Circle: " + perimeter);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius " + radius);
    }
}

public class p2 {
    public static void main(String[] args) {
        Circle c = new Circle(5);
        c.calculateArea();
        c.calculatePerimeter();
        c.draw();
    }
}
