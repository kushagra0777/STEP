import java.util.*;
public class p1 {
    public static String[] splitWords(String text) {
        int n = text.length();
        String[] temp = new String[n];
        int count = 0, start = 0;
        for (int i = 0; i <= n; i++) {
            boolean isBoundary = (i == n) || text.charAt(i) == ' ' || !Character.isLetter(text.charAt(i));
            if (isBoundary) {
                if (start < i) {
                    temp[count++] = text.substring(start, i);
                }
                start = i + 1;
            }
        }
        String[] words = new String[count];
        System.arraycopy(temp, 0, words, 0, count);
        return words;
    }
    public static int stringDistance(String w1, String w2) {
        int len1 = w1.length(), len2 = w2.length();
        int minLen = Math.min(len1, len2);
        int diff = Math.abs(len1 - len2);
        for (int i = 0; i < minLen; i++) {
            if (w1.charAt(i) != w2.charAt(i)) diff++;
        }
        return diff;
    }
    public static String findSuggestion(String word, String[] dict) {
        int minDist = Integer.MAX_VALUE;
        String suggestion = word;
        for (String d : dict) {
            int dist = stringDistance(word.toLowerCase(), d.toLowerCase());
            if (dist < minDist) {
                minDist = dist;
                suggestion = d;
            }
        }
        return (minDist <= 2 && !suggestion.equalsIgnoreCase(word)) ? suggestion : word;
    }

    public static void displayResults(String[] words, String[] dict) {
        System.out.printf("%-12s , %-12s , %-7s , %-10s\n", "Original", "Suggestion", "Distance", "Status");
        for (String w : words) {
            String suggestion = findSuggestion(w, dict);
            int dist = stringDistance(w.toLowerCase(), suggestion.toLowerCase());
            String status = (dist == 0) ? "Correct" : (dist <= 2 ? "Misspelled" : "Unknown");
            System.out.printf("%-12s , %-12s , %-7d , %-10s\n", w, suggestion, dist, status);
        }
    }
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();
        System.out.println("Enter dictionary words (comma separated):");
        String dictInput = sc.nextLine();
        String[] dict = splitWords(dictInput.replace(",", " "));
        String[] words = splitWords(sentence);
        System.out.println("\nSpell Check Report:");
        displayResults(words, dict);
    }
}