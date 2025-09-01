import java.util.*;

class Product {
    private static int seq = 0;
    private static int totalProducts = 0;
    private static String[] categories = {"Electronics","Clothing","Grocery","Books","Home"};
    final String productId;
    final String productName;
    final double price;
    final String category;
    int stockQuantity;

    public Product(String productName, double price, String category, int stockQuantity) {
        this.productId = String.format("P%03d", ++seq);
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        totalProducts++;
    }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p: products) if (p != null && p.productId.equals(productId)) return p;
        return null;
    }

    public static Product[] getProductsByCategory(Product[] products, String category) {
        Product[] temp = new Product[products.length];
        int k=0;
        for (Product p: products) if (p != null && p.category.equalsIgnoreCase(category)) temp[k++]=p;
        return Arrays.copyOf(temp, k);
    }

    public static int getTotalProducts() { return totalProducts; }
    public static String[] getCategories() { return categories; }
}

class p2 {
    private static int seq = 0;
    final String cartId;
    final String customerName;
    private Product[] products;
    private int[] quantities;
    private int count;
    private double cartTotal;

    public p2(String customerName) {
        this.cartId = String.format("C%03d", ++seq);
        this.customerName = customerName;
        this.products = new Product[50];
        this.quantities = new int[50];
        this.count = 0;
        this.cartTotal = 0;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0 || product.stockQuantity < quantity) return;
        int idx = indexOf(product.productId);
        if (idx == -1) {
            products[count] = product;
            quantities[count] = quantity;
            count++;
        } else {
            quantities[idx] += quantity;
        }
        product.stockQuantity -= quantity;
        calculateTotal();
    }

    public void removeProduct(String productId) {
        int idx = indexOf(productId);
        if (idx != -1) {
            products[idx].stockQuantity += quantities[idx];
            shiftLeft(idx);
            count--;
            calculateTotal();
        }
    }

    private int indexOf(String productId) {
        for (int i=0;i<count;i++) if (products[i].productId.equals(productId)) return i;
        return -1;
    }

    private void shiftLeft(int from) {
        for (int i=from;i<count-1;i++) {
            products[i]=products[i+1];
            quantities[i]=quantities[i+1];
        }
        products[count-1]=null;
        quantities[count-1]=0;
    }

    public void calculateTotal() {
        cartTotal = 0;
        for (int i=0;i<count;i++) cartTotal += products[i].price * quantities[i];
    }

    public void displayCart() {
        System.out.println("Cart " + cartId + " | Customer: " + customerName);
        for (int i=0;i<count;i++) {
            System.out.println(products[i].productId + " - " + products[i].productName + " x " + quantities[i] + " = " + (products[i].price*quantities[i]));
        }
        System.out.println("Total: " + cartTotal);
    }

    public void checkout() {
        if (count==0) { System.out.println("Cart is empty"); return; }
        displayCart();
        System.out.println("Checkout complete for " + customerName + ". Amount paid: " + cartTotal);
        count=0; Arrays.fill(products,null); Arrays.fill(quantities,0); cartTotal=0;
    }

    public static void main(String[] args) {
        Product[] store = new Product[12];
        store[0]=new Product("Laptop",75000,"Electronics",10);
        store[1]=new Product("Headphones",2500,"Electronics",30);
        store[2]=new Product("T-Shirt",799,"Clothing",50);
        store[3]=new Product("Jeans",1999,"Clothing",40);
        store[4]=new Product("Apples 1kg",150,"Grocery",100);
        store[5]=new Product("Coffee 200g",350,"Grocery",60);
        store[6]=new Product("Novel",499,"Books",80);
        store[7]=new Product("Cookbook",899,"Books",20);
        store[8]=new Product("Mixer",3499,"Home",25);
        store[9]=new Product("Kettle",1299,"Home",25);
        store[10]=new Product("USB Cable",199,"Electronics",100);
        store[11]=new Product("Notebook",99,"Books",200);

        p2 cart = new p2("Alice");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.List All  2.List By Category  3.Add  4.Remove  5.View Cart  6.Checkout  0.Exit");
            int ch = -1;
            try { ch = Integer.parseInt(sc.nextLine().trim()); } catch(Exception e){}
            if (ch==0) break;
            if (ch==1) {
                for (Product p: store) System.out.println(p.productId+" | "+p.productName+" | "+p.category+" | "+p.price+" | Stock:"+p.stockQuantity);
            } else if (ch==2) {
                System.out.println("Categories: "+String.join(", ", Product.getCategories()));
                String cat = sc.nextLine().trim();
                Product[] arr = Product.getProductsByCategory(store, cat);
                for (Product p: arr) System.out.println(p.productId+" | "+p.productName+" | "+p.category+" | "+p.price+" | Stock:"+p.stockQuantity);
            } else if (ch==3) {
                System.out.print("Product ID: "); String pid = sc.nextLine().trim();
                System.out.print("Quantity: "); int q = Integer.parseInt(sc.nextLine().trim());
                cart.addProduct(Product.findProductById(store,pid), q);
            } else if (ch==4) {
                System.out.print("Product ID: "); String pid = sc.nextLine().trim();
                cart.removeProduct(pid);
            } else if (ch==5) {
                cart.displayCart();
            } else if (ch==6) {
                cart.checkout();
            }
        }
        sc.close();
    }
}
