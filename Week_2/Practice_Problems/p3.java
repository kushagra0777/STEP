import java.util.*;

public class p3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();
        for (char ch : input.toCharArray()) {
            System.out.printf("Char: '%c' | ASCII: %d | Type: %s%n", 
                              ch, (int) ch, classifyCharacter(ch));
            if (Character.isLetter(ch)) {
                char toggled = toggleCase(ch);
                System.out.printf("Toggled: '%c' (ASCII: %d)%n", toggled, (int) toggled);
                System.out.printf("ASCII difference between cases: %d%n", 
                                  Math.abs(ch - toggled));
            }
        }
        System.out.println("Caesar Cipher (shift 3): " + caesarCipher(input, 3));
        displayASCIITable(65, 90);
        int[] asciiArr = stringToASCII(input);
        System.out.println("ASCII Array: " + Arrays.toString(asciiArr));
        System.out.println("Back to String: " + asciiToString(asciiArr));
        scanner.close();
    }

    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        if (Character.isLowerCase(ch)) return "Lowercase Letter";
        if (Character.isDigit(ch)) return "Digit";
        return "Special Character";
    }

    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) return (char) (ch + 32);
        if (Character.isLowerCase(ch)) return (char) (ch - 32);
        return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                sb.append((char) ((ch - base + shift) % 26 + base));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.printf("%d -> %c%n", i, (char) i);
        }
    }

    public static int[] stringToASCII(String text) {
        int[] ascii = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            ascii[i] = text.charAt(i);
        }
        return ascii;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}
