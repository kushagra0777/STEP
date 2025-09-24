public class p1 {
    void calculate(int distance) {
        System.out.println("Basic Delivery: Distance " + distance + " km, Cost = " + (distance * 10));
    }
    void calculate(int distance, int priorityFee) {
        int cost = distance * 10 + priorityFee;
        System.out.println("Premium Delivery: Distance " + distance + " km + Priority Fee " + priorityFee + ", Cost = " + cost);
    }
    void calculate(int distance, int orders, double discount) {
        double cost = distance * 10 - (orders * discount);
        System.out.println("Group Delivery: Distance " + distance + " km with " + orders + " orders discount, Cost = " + cost);
    }
    void calculate(int distance, double discountPercent, int freeLimit) {
        int base = distance * 10;
        double cost = base - (base * discountPercent / 100);
        if (base > freeLimit) cost = 0;
        System.out.println("Festival Special: Distance " + distance + " km, Discount " + discountPercent + "%, Final Cost = " + cost);
    }
    public static void main(String[] args) {
        p1 d = new p1();
        d.calculate(5);
        d.calculate(5, 20);
        d.calculate(10, 3, 5.0);
        d.calculate(12, 20.0, 100);
    }
}
