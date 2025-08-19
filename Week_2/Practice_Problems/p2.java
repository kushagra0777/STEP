import java.util.*;

public class p2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence with mixed formatting:");
        String input = scanner.nextLine();
        String trimmed = input.trim();
        System.out.println("Trimmed: " + trimmed);
        String underscored = trimmed.replace(" ", "_");
        System.out.println("Spaces replaced: " + underscored);
        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("Without digits: " + noDigits);
        String[] words = trimmed.split("\\s+");
        System.out.println("Words array: " + Arrays.toString(words));
        String joined = String.join(" | ", words);
        System.out.println("Joined: " + joined);
        String noPunctuation = removePunctuation(trimmed);
        System.out.println("Without punctuation: " + noPunctuation);
        String capitalized = capitalizeWords(noPunctuation);
        System.out.println("Capitalized: " + capitalized);
        String reversedOrder = reverseWordOrder(noPunctuation);
        System.out.println("Reversed word order: " + reversedOrder);
        countWordFrequency(noPunctuation);
        scanner.close();
    }

    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> freq = new LinkedHashMap<>();
        for (String w : words) {
            freq.put(w, freq.getOrDefault(w, 0) + 1);
        }
        System.out.println("Word frequencies: " + freq);
    }
}
