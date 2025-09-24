class Post {
    String author, content, time;
    Post(String a, String c, String t) {
        author = a; content = c; time = t;
    }
    void display() {
        System.out.println(author + ": " + content + " (" + time + ")");
    }
}
class InstagramPost extends Post {
    InstagramPost(String a, String c, String t) { super(a,c,t); }
    void display() {
        System.out.println(author + " posted on Instagram: " + content + " #fun #life | Likes: 500 (" + time + ")");
    }
}
class TwitterPost extends Post {
    TwitterPost(String a, String c, String t) { super(a,c,t); }
    void display() {
        System.out.println(author + " tweeted: " + content + " (" + content.length() + " chars, Retweets: 100) at " + time);
    }
}
class LinkedInPost extends Post {
    LinkedInPost(String a, String c, String t) { super(a,c,t); }
    void display() {
        System.out.println("LinkedIn Update by " + author + ": " + content + " | Connections engaged: 50 (" + time + ")");
    }
}
public class p2 {
    public static void main(String[] args) {
        Post p1 = new InstagramPost("Alice", "Loving the vibes!", "10:00 AM");
        Post p2 = new TwitterPost("Bob", "Hello World!", "11:00 AM");
        Post p3 = new LinkedInPost("Charlie", "Excited about my new role!", "12:00 PM");
        p1.display();
        p2.display();
        p3.display();
    }
}
