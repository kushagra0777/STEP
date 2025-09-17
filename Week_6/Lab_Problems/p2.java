class Phone {
    protected String brand;
    protected String model;

    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone constructor called: " + brand + " " + model);
    }
}

public class p2 extends Phone {
    private String operatingSystem;

    public p2(String brand, String model, String os) {
        super(brand, model);
        this.operatingSystem = os;
        System.out.println("SmartPhone constructor called: " + os);
    }

    public static void main(String[] args) {
        p2 sp1 = new p2("Apple", "iPhone 15", "iOS");
        p2 sp2 = new p2("Samsung", "Galaxy S25", "Android");
    }
}
