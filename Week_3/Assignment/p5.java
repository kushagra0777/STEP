import java.util.*;

class Book {
    private static int bseq=0;
    static int totalBooks=0;
    static String libraryName="Unset";
    static double finePerDay=2.0;
    static int maxBooksAllowed=3;

    final String bookId;
    final String title;
    final String author;
    final String isbn;
    final String category;
    boolean isIssued=false;
    String issueDate="";
    String dueDate="";
    int popularity=0;

    public Book(String title,String author,String isbn,String category){
        this.bookId=String.format("B%04d", ++bseq);
        this.title=title; this.author=author; this.isbn=isbn; this.category=category;
        totalBooks++;
    }

    public void display(){ System.out.println(bookId+" | "+title+" | "+author+" | "+category+" | Issued: "+isIssued); }
}

class Member {
    private static int mseq=0;
    static int totalMembers=0;

    final String memberId;
    final String memberName;
    final String memberType;
    final String membershipDate;
    Book[] booksIssued;
    double totalFines=0;

    public Member(String memberName,String memberType,String membershipDate){
        this.memberId=String.format("M%04d", ++mseq);
        this.memberName=memberName; this.memberType=memberType; this.membershipDate=membershipDate;
        int cap = memberType.equalsIgnoreCase("Faculty")?5:(memberType.equalsIgnoreCase("Student")?3:2);
        booksIssued = new Book[cap];
        totalMembers++;
    }

    public boolean issueBook(Book b, String issueDate, String dueDate){
        if (b.isIssued) return false;
        for (int i=0;i<booksIssued.length;i++){
            if (booksIssued[i]==null){
                booksIssued[i]=b; b.isIssued=true; b.issueDate=issueDate; b.dueDate=dueDate; b.popularity++; return true;
            }
        }
        return false;
    }

    public boolean returnBook(String bookId, String returnDate){
        for (int i=0;i<booksIssued.length;i++){
            if (booksIssued[i]!=null && booksIssued[i].bookId.equals(bookId)){
                int overdue = diffDays(booksIssued[i].dueDate, returnDate);
                if (overdue>0) totalFines += overdue * Book.finePerDay;
                booksIssued[i].isIssued=false; booksIssued[i].issueDate=""; booksIssued[i].dueDate="";
                booksIssued[i]=null;
                return true;
            }
        }
        return false;
    }

    public boolean renewBook(String bookId, String newDueDate){
        for (Book b: booksIssued) if (b!=null && b.bookId.equals(bookId)){ b.dueDate=newDueDate; return true; }
        return false;
    }

    private int diffDays(String due, String ret){
        try {
            String[] d=due.split("-"), r=ret.split("-");
            Calendar cd=Calendar.getInstance(); cd.set(Integer.parseInt(d[0]), Integer.parseInt(d[1])-1, Integer.parseInt(d[2]));
            Calendar cr=Calendar.getInstance(); cr.set(Integer.parseInt(r[0]), Integer.parseInt(r[1])-1, Integer.parseInt(r[2]));
            long ms = cr.getTimeInMillis()-cd.getTimeInMillis();
            int days=(int)(ms/(1000*60*60*24));
            return Math.max(days,0);
        } catch(Exception e){ return 0; }
    }

    public void display(){ System.out.println(memberId+" | "+memberName+" | "+memberType+" | Fines: "+totalFines); }
}

class p5 {
    public static void generateLibraryReport(Book[] books, Member[] members){
        int issued=0; for (Book b: books) if (b!=null && b.isIssued) issued++;
        System.out.println("Library: "+Book.libraryName+" | Books: "+Book.totalBooks+" | Members: "+Member.totalMembers+" | Issued: "+issued);
    }
    public static Book[] getOverdueBooks(Book[] books, String today){
        List<Book> list=new ArrayList<>();
        for (Book b: books) if (b!=null && b.isIssued){
            String dd=b.dueDate;
            try{
                String[] d=dd.split("-"), r=today.split("-");
                Calendar cd=Calendar.getInstance(); cd.set(Integer.parseInt(d[0]), Integer.parseInt(d[1])-1, Integer.parseInt(d[2]));
                Calendar cr=Calendar.getInstance(); cr.set(Integer.parseInt(r[0]), Integer.parseInt(r[1])-1, Integer.parseInt(r[2]));
                if (cr.after(cd)) list.add(b);
            }catch(Exception e){}
        }
        return list.toArray(new Book[0]);
    }
    public static Book[] getMostPopularBooks(Book[] books){
        Arrays.sort(books, new Comparator<Book>(){
            public int compare(Book a, Book b){
                if (a==null && b==null) return 0; if (a==null) return 1; if (b==null) return -1;
                return Integer.compare(b.popularity,a.popularity);
            }
        });
        return books;
    }

    public static void main(String[] args){
        Book.libraryName="City Library"; Book.finePerDay=3.0; Book.maxBooksAllowed=3;

        Book[] stock = {
            new Book("Java Basics","A. Author","ISBN1","Programming"),
            new Book("Algorithms","B. Author","ISBN2","Programming"),
            new Book("Databases","C. Author","ISBN3","Tech"),
            new Book("Physics","D. Author","ISBN4","Science")
        };

        Member s = new Member("Alice","Student","2025-01-01");
        Member f = new Member("Dr. Bob","Faculty","2024-03-12");

        s.issueBook(stock[0],"2025-08-01","2025-08-10");
        s.issueBook(stock[1],"2025-08-01","2025-08-10");
        f.issueBook(stock[2],"2025-08-02","2025-08-12");

        s.returnBook(stock[0].bookId,"2025-08-15");
        f.renewBook(stock[2].bookId,"2025-08-20");

        for (Book b: stock) b.display();
        s.display(); f.display();

        generateLibraryReport(stock, new Member[]{s,f});
        Book[] overdue = getOverdueBooks(stock,"2025-08-16");
        System.out.println("Overdue:");
        for (Book b: overdue) System.out.println(b.bookId+" "+b.title);
        System.out.println("Popular:");
        for (Book b: getMostPopularBooks(stock)) if (b!=null) System.out.println(b.title+" -> "+b.popularity);
    }
}
