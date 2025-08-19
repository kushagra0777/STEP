import java.util.*;

public class p4 {
    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) sb.append(word.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void analyzeText(String text) {
        String cleaned = text.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = cleaned.split("\\s+");
        int wordCount = words.length;
        int sentenceCount = text.split("[.!?]").length;
        int charCount = cleaned.replace(" ", "").length();
        String longestWord = "";
        Map<Character, Integer> charFreq = new HashMap<>();
        for (String w : words) {
            if (w.length() > longestWord.length()) longestWord = w;
            for (char c : w.toCharArray()) {
                charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
            }
        }
        char mostCommonChar = ' ';
        int maxFreq = 0;
        for (Map.Entry<Character, Integer> e : charFreq.entrySet()) {
            if (e.getValue() > maxFreq) {
                maxFreq = e.getValue();
                mostCommonChar = e.getKey();
            }
        }
        System.out.println("Words: " + wordCount);
        System.out.println("Sentences: " + sentenceCount);
        System.out.println("Characters (no spaces): " + charCount);
        System.out.println("Longest word: " + longestWord);
        System.out.println("Most common character: " + mostCommonChar);
    }

    public static String[] getWordsSorted(String text) {
        String cleaned = text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
        String[] words = cleaned.split("\\s+");
        Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("TEXT PROCESSOR ");
        System.out.print("Enter a paragraph: ");
        String paragraph = scanner.nextLine();
        String cleaned = cleanInput(paragraph);
        analyzeText(cleaned);
        String[] sortedWords = getWordsSorted(cleaned);
        System.out.println("Words in alphabetical order:");
        for (String w : sortedWords) System.out.println(w);
        System.out.print("Enter a word to search: ");
        String search = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (String w : sortedWords) {
            if (w.equals(search)) {
                found = true;
                break;
            }
        }
        System.out.println("Word found: " + found);
        scanner.close();
    }
}
