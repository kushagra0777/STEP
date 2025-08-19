import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter your favorite programming language: ");
        String language = scanner.nextLine();

        System.out.print("Enter a sentence about your programming experience: ");
        String sentence = scanner.nextLine();

        String[] parts = fullName.trim().split("\\s+");
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[parts.length - 1] : "";

        int charCount = sentence.replace(" ", "").length();
        String langUpper = language.toUpperCase();

        System.out.println("\n=== Summary ===");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Favorite Language (Uppercase): " + langUpper);
        System.out.println("Sentence Character Count (no spaces): " + charCount);

        scanner.close();
    }
}
