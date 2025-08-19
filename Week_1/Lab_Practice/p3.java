import java.util.Scanner;

public class p3 {

    public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {}
        return count;
    }

    public static String[] customSplit(String text) {
        int length = findLength(text);
        int wordCount = 1;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) == ' ') wordCount++;
        }

        String[] words = new String[wordCount];
        int start = 0, index = 0;

        for (int i = 0; i <= length; i++) {
            if (i == length || text.charAt(i) == ' ') {
                StringBuilder word = new StringBuilder();
                for (int j = start; j < i; j++) {
                    word.append(text.charAt(j));
                }
                words[index++] = word.toString();
                start = i + 1;
            }
        }
        return words;
    }

    public static String[][] wordsWithLengths(String[] words) {
        String[][] result = new String[words.length][2];
        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(findLength(words[i]));
        }
        return result;
    }

    // Display 2D array in table
    public static void displayTable(String[][] data) {
        System.out.printf("%-15s %-10s%n", "Word", "Length");
        for (String[] row : data) {
            System.out.printf("%-15s %-10d%n", row[0], Integer.parseInt(row[1]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        String[] words = customSplit(input);
        String[][] table = wordsWithLengths(words);

        displayTable(table);
    }
}
