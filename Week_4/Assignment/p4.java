class FoodOrder {
    String customerName, foodItem;
    int quantity;
    double price;

    public FoodOrder() {
        this("Unknown", "Unknown", 0, 0.0);
    }

    public FoodOrder(String foodItem) {
        this("Customer", foodItem, 1, 100.0);
    }

    public FoodOrder(String foodItem, int quantity) {
        this("Customer", foodItem, quantity, quantity * 100.0);
    }

    public FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    public void printBill() {
        System.out.println("Customer: " + customerName + ", Food: " + foodItem + ", Quantity: " + quantity + ", Total Price: " + price);
    }
}

public class p4 {
    public static void main(String[] args) {
        FoodOrder o1 = new FoodOrder();
        FoodOrder o2 = new FoodOrder("Burger");
        FoodOrder o3 = new FoodOrder("Pizza", 3);
        FoodOrder o4 = new FoodOrder("Alice", "Pasta", 2, 400.0);
        o1.printBill();
        o2.printBill();
        o3.printBill();
        o4.printBill();
    }
}
