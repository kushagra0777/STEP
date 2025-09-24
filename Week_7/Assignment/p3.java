class Vehicle {
    void dispatch() { System.out.println("Generic vehicle dispatched"); }
}
class Bus extends Vehicle {
    void dispatch() { System.out.println("Bus dispatched on fixed route with 50 passengers capacity"); }
}
class Taxi extends Vehicle {
    void dispatch() { System.out.println("Taxi dispatched for door-to-door service, Fare by distance"); }
}
class Train extends Vehicle {
    void dispatch() { System.out.println("Train dispatched with multiple cars on schedule"); }
}
class Bike extends Vehicle {
    void dispatch() { System.out.println("Bike dispatched for short eco-friendly trip"); }
}
public class p3 {
    public static void main(String[] args) {
        Vehicle[] fleet = { new Bus(), new Taxi(), new Train(), new Bike() };
        for (Vehicle v : fleet) v.dispatch();
    }
}
