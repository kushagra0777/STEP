public class p1 {
    public static void main(String[] args) {
        Car defaultCar = new Car();
        System.out.println(defaultCar.getVehicleInfo());
        defaultCar.start();
        defaultCar.displaySpecs();
        defaultCar.openTrunk();
        defaultCar.playRadio();

        Car paramCar = new Car("Toyota", "Camry", 2024, "Hybrid", 4, "Hybrid", "Automatic");
        System.out.println(paramCar.getVehicleInfo());
        paramCar.start();
        paramCar.displaySpecs();
        paramCar.openTrunk();
        paramCar.playRadio();
    }
}

class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;

    private String registrationNumber;
    private boolean isRunning;

    public Vehicle() {
        this.brand = "Generic";
        this.model = "Standard";
        this.year = 2025;
        this.engineType = "Petrol";
        this.registrationNumber = "REG-" + (int)(Math.random()*10000);
        this.isRunning = false;
        System.out.println("Vehicle default constructor called");
    }

    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.registrationNumber = "REG-" + (int)(Math.random()*10000);
        this.isRunning = false;
        System.out.println("Vehicle parameterized constructor called");
    }

    public void start() { isRunning = true; System.out.println("Vehicle started"); }
    public void stop() { isRunning = false; System.out.println("Vehicle stopped"); }
    public String getVehicleInfo() {
        return "Brand: " + brand + ", Model: " + model + ", Year: " + year + ", Engine: " + engineType +
                ", Registration: " + registrationNumber + ", Running: " + isRunning;
    }
    public void displaySpecs() { System.out.println("Engine Type: " + engineType + ", Year: " + year); }
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public boolean isRunning() { return isRunning; }
}

class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    public Car() {
        super();
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Automatic";
        System.out.println("Car default constructor called");
    }

    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Car specific startup sequence executed");
    }

    @Override
    public void displaySpecs() {
        super.displaySpecs();
        System.out.println("Doors: " + numberOfDoors + ", Fuel: " + fuelType + ", Transmission: " + transmissionType);
    }

    public void openTrunk() { System.out.println("Trunk opened"); }
    public void playRadio() { System.out.println("Radio playing music"); }
}
