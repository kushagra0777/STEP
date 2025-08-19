import java.util.Scanner;

public class p2 {
    public static int findLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {}
        return count;
    }
    public static char[] getUniqueCharacters(String text) {
        int length = findLength(text);
        char[] temp = new char[length];
        int uniqueCount = 0;
        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == c) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) temp[uniqueCount++] = c;
        }
        char[] unique = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) unique[i] = temp[i];
        return unique;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();
        char[] uniqueChars = getUniqueCharacters(input);
        System.out.print("Unique characters: ");
        for (char c : uniqueChars) System.out.print(c + " ");
    }
}
