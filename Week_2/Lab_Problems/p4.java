import java.util.*;

public class p4 {
    public static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                sb.append((char)('A' + (ch - 'A' + shift + 26) % 26));
            } else if (ch >= 'a' && ch <= 'z') {
                sb.append((char)('a' + (ch - 'a' + shift + 26) % 26));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    public static String decrypt(String text, int shift) {
        return encrypt(text, shift);
    }
    public static void displayAscii(String label, String text) {
        System.out.print(label + ": ");
        for (char ch : text.toCharArray()) {
            System.out.print("'" + ch + "'(" + (int)ch + ") ");
        }
        System.out.println();
    }
    public static boolean validate(String original, String decrypted) {
        return original.equals(decrypted);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to encrypt: ");
        String text = sc.nextLine();
        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();
        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);
        displayAscii("Original", text);
        displayAscii("Encrypted", encrypted);
        displayAscii("Decrypted", decrypted);
        System.out.println("Does Decrypted matches original? " + validate(text, decrypted));
    }
}