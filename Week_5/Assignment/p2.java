import java.time.LocalDateTime;
import java.util.*;

final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId, String name, String category, String manufacturer,
                    double basePrice, double weight, String[] features, Map<String, String> specifications) {
        if (productId == null || productId.isBlank()) throw new IllegalArgumentException("Product ID required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Category required");
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = (features != null) ? features.clone() : new String[0];
        this.specifications = (specifications != null) ? new HashMap<>(specifications) : new HashMap<>();
    }

    public static Product createElectronics(String id, String name, double price) {
        return new Product(id, name, "Electronics", "GenericManufacturer", price, 1.0, new String[]{"Warranty"}, Map.of("Voltage", "220V"));
    }

    public static Product createClothing(String id, String name, double price) {
        return new Product(id, name, "Clothing", "GenericBrand", price, 0.5, new String[]{"Size M"}, Map.of("Material", "Cotton"));
    }

    public static Product createBooks(String id, String name, double price) {
        return new Product(id, name, "Books", "GenericPublisher", price, 0.3, new String[]{"Paperback"}, Map.of("Pages", "300"));
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return features.clone(); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }
    public final double calculateTax(String region) { return basePrice * 0.1; }

    @Override
    public String toString() { return name + " [" + category + "] - $" + basePrice; }
}

class Customer {
    private final String customerId;
    private final String email;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;
    private final String accountCreationDate;

    public Customer(String email) {
        this(UUID.randomUUID().toString(), email, "Guest", null, "EN", LocalDateTime.now().toString());
    }

    public Customer(String customerId, String email, String name, String phoneNumber, String preferredLanguage, String accountCreationDate) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage;
        this.accountCreationDate = accountCreationDate;
    }

    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public void setName(String name) { if (name != null) this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    String getCreditRating() { return "Good"; }
    public String getPublicProfile() { return "Customer: " + name + " (" + email + ")"; }
}

class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<Object> items = new ArrayList<>();
    private double totalAmount;
    private int itemCount;

    public ShoppingCart(String customerId) {
        this.cartId = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.totalAmount = 0;
        this.itemCount = 0;
    }

    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product) || quantity <= 0) return false;
        items.add(product);
        totalAmount += ((Product) product).getBasePrice() * quantity - calculateDiscount();
        itemCount += quantity;
        return true;
    }

    private double calculateDiscount() { return totalAmount * 0.05; }
    String getCartSummary() { return "Cart[" + cartId + "] Items: " + itemCount + " Total: $" + totalAmount; }
}

class Order {
    private final String orderId;
    private final LocalDateTime orderTime;

    public Order() {
        this(UUID.randomUUID().toString(), LocalDateTime.now());
    }

    public Order(String orderId, LocalDateTime orderTime) {
        this.orderId = orderId;
        this.orderTime = orderTime;
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
}

class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String processorId, String securityKey) {
        this.processorId = processorId;
        this.securityKey = securityKey;
    }

    public String getProcessorId() { return processorId; }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    public ShippingCalculator(Map<String, Double> shippingRates) { this.shippingRates = new HashMap<>(shippingRates); }
    public Map<String, Double> getShippingRates() { return new HashMap<>(shippingRates); }
}

final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    public static boolean processOrder(Object order, Object customer) {
        if (!(order instanceof Order) || !(customer instanceof Customer)) return false;
        return true;
    }

    public static void addProduct(Product product) { productCatalog.put(product.getProductId(), product); }
    public static Product getProduct(String id) { return (Product) productCatalog.get(id); }
}

public class p2 {
    public static void main(String[] args) {
        Product laptop = Product.createElectronics("P1", "Laptop", 1200);
        Product tshirt = Product.createClothing("P2", "T-Shirt", 20);

        Customer c = new Customer("user@example.com");
        ShoppingCart cart = new ShoppingCart(c.getCustomerId());
        cart.addItem(laptop, 1);
        cart.addItem(tshirt, 2);

        ECommerceSystem.addProduct(laptop);
        ECommerceSystem.addProduct(tshirt);

        Order o = new Order();
        System.out.println("Order processed: " + ECommerceSystem.processOrder(o, c));
        System.out.println(cart.getCartSummary());
        System.out.println(c.getPublicProfile());
    }
}
