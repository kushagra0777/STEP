class Book {
    String title;
    String author;
    double price;
    Book() {
        title = "Unknown";
        author = "Unknown";
        price = 0;
    }
    Book(String t, String a, double p) {
        title = t;
        author = a;
        price = p;
    }
    void displayDetails() {
        System.out.println("Title: " + title + "\nAuthor: " + author + "\nPrice: " + price);
    }
}
public class p1 {
    public static void main(String[] args) {
        Book b1 = new Book();
        Book b2 = new Book("Computer Science", "Dr. Sandhya Singh", 300);
        b1.displayDetails();
        b2.displayDetails();
    }
}