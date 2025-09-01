class Book {
    private static int bookCounter = 0;
    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookId = generateBookId();
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }

    private static String generateBookId() {
        bookCounter++;
        return String.format("B%03d", bookCounter);
    }

    public void issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
        }
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public String getBookId() {
        return bookId;
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable);
    }
}

class Member {
    private static int memberCounter = 0;
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;

    public Member(String memberName) {
        this.memberName = memberName;
        this.memberId = generateMemberId();
        this.booksIssued = new String[5];
        this.bookCount = 0;
    }

    private static String generateMemberId() {
        memberCounter++;
        return String.format("M%03d", memberCounter);
    }

    public void borrowBook(Book book) {
        if (book.getAvailability() && bookCount < 5) {
            book.issueBook();
            booksIssued[bookCount++] = book.getBookId();
        }
    }

    public void returnBook(String bookId, Book[] books) {
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        b.returnBook();
                        break;
                    }
                }
                booksIssued[i] = booksIssued[bookCount - 1];
                booksIssued[bookCount - 1] = null;
                bookCount--;
                break;
            }
        }
    }

    public void displayMemberInfo() {
        System.out.print("Member ID: " + memberId + ", Name: " + memberName + ", Books Issued: ");
        for (int i = 0; i < bookCount; i++) System.out.print(booksIssued[i] + " ");
        System.out.println();
    }
}

public class p2 {
    public static void main(String[] args) {
        Book[] books = {
            new Book("Java Programming", "Author A"),
            new Book("Python Basics", "Author B"),
            new Book("C++ Guide", "Author C")
        };

        Member m1 = new Member("Alice");
        Member m2 = new Member("Bob");

        m1.borrowBook(books[0]);
        m2.borrowBook(books[1]);
        m1.borrowBook(books[2]);

        for (Book b : books) b.displayBookInfo();
        m1.displayMemberInfo();
        m2.displayMemberInfo();

        m1.returnBook("B001", books);
        for (Book b : books) b.displayBookInfo();
    }
}
