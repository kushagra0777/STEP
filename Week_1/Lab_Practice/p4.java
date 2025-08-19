import java.util.Scanner;

public class p4 {

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

    public static int[] findShortestAndLongest(String[][] data) {
        int shortestIndex = 0, longestIndex = 0;
        for (int i = 1; i < data.length; i++) {
            int length = Integer.parseInt(data[i][1]);
            if (length < Integer.parseInt(data[shortestIndex][1])) shortestIndex = i;
            if (length > Integer.parseInt(data[longestIndex][1])) longestIndex = i;
        }
        return new int[]{shortestIndex, longestIndex};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine();

        String[] words = customSplit(input);
        String[][] table = wordsWithLengths(words);

        int[] indexes = findShortestAndLongest(table);

        System.out.println("Shortest word: " + table[indexes[0]][0] +
                           " (" + table[indexes[0]][1] + ")");
        System.out.println("Longest word: " + table[indexes[1]][0] +
                           " (" + table[indexes[1]][1] + ")");
    }
}
