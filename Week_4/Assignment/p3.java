class Book {
    String title, author, isbn;
    boolean isAvailable;

    public Book() {
        this("", "", "", true);
    }

    public Book(String title, String author) {
        this(title, author, "N/A", true);
    }

    public Book(String title, String author, String isbn) {
        this(title, author, isbn, true);
    }

    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public void borrowBook() {
        if (isAvailable) isAvailable = false;
        else System.out.println("Book not available");
    }

    public void returnBook() {
        isAvailable = true;
    }

    public void displayBookInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + isAvailable);
    }
}

public class p3 {
    public static void main(String[] args) {
        Book b1 = new Book();
        Book b2 = new Book("1984", "George Orwell");
        Book b3 = new Book("The Hobbit", "J.R.R. Tolkien", "12345");
        b2.borrowBook();
        b2.displayBookInfo();
        b2.returnBook();
        b2.displayBookInfo();
        b1.displayBookInfo();
        b3.displayBookInfo();
    }
}
