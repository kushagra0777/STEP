import java.time.*;
public class p1 {
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    public p1(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false;
    }
    public void startEngine() {
        isRunning = true;
        System.out.println(brand + " " + model + " engine started.");
    }
    public void stopEngine() {
        isRunning = false;
        System.out.println(brand + " " + model + " engine stopped.");
    }
    public void displayInfo() {
        System.out.println("Car Info: " + brand + " " + model + ", Year: " + year + ", Color: " + color + ", Running: " + isRunning);
    }
    public int getAge() {
        return Year.now().getValue() - year;
    }
    public static void main(String[] args) {
        p1 car1 = new p1("Toyota", "Corolla", 2015, "Red");
        p1 car2 = new p1("Honda", "Civic", 2020, "Black");
        p1 car3 = new p1("Tesla", "Model 3", 2022, "White");
        car1.startEngine();
        car1.displayInfo();
        System.out.println("Car age: " + car1.getAge() + " years\n");
        car2.displayInfo();
        car2.startEngine();
        car2.stopEngine();
        System.out.println("Car age: " + car2.getAge() + " years\n");
        car3.displayInfo();
        car3.startEngine();
        System.out.println("Car age: " + car3.getAge() + " years\n");
    }
}
