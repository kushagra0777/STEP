abstract class Vehicle {
    protected int speed;
    protected String fuelType;

    public Vehicle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    public abstract void startEngine();
}

interface Maintainable {
    void serviceInfo();
}

class Car extends Vehicle implements Maintainable {
    private String model;

    public Car(int speed, String fuelType, String model) {
        super(speed, fuelType);
        this.model = model;
    }

    @Override
    public void startEngine() {
        System.out.println("Car " + model + " starts engine with " + fuelType + " fuel.");
    }

    @Override
    public void serviceInfo() {
        System.out.println("Service every 10,000 km or 6 months.");
    }
}

public class p3 {
    public static void main(String[] args) {
        Car c = new Car(120, "Petrol", "Honda City");
        c.startEngine();
        c.serviceInfo();
    }
}
