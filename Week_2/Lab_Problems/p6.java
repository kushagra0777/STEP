import java.util.*;
public class p6 {
    public static String[] splitWords(String text) {
        int n = text.length();
        String[] temp = new String[n]; 
        int count = 0;
        int start = 0;
        for (int i = 0; i <= n; i++) {
            if (i == n || text.charAt(i) == ' ') {
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
    public static String[] justifyText(String[] words, int width) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int n = words.length;
        java.util.List<String> lines = new java.util.ArrayList<>();
        while (i < n) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < n && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            int gaps = j - i - 1;
            sb.setLength(0);
            if (j == n || gaps == 0) { 
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(' ');
                }
                while (sb.length() < width) sb.append(' ');
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) sb.append(' ');
                        if (extra > 0) {
                            sb.append(' ');
                            extra--;
                        }
                    }
                }
            }
            lines.add(sb.toString());
            i = j;
        }
        return lines.toArray(new String[0]);
    }

    public static String[] centerAlignText(String[] words, int width) {
        java.util.List<String> lines = new java.util.ArrayList<>();
        int i = 0, n = words.length;
        while (i < n) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < n && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            StringBuilder sb = new StringBuilder();
            int padding = (width - lineLen) / 2;
            for (int p = 0; p < padding; p++) sb.append(' ');
            for (int k = i; k < j; k++) {
                sb.append(words[k]);
                if (k < j - 1) sb.append(' ');
            }
            while (sb.length() < width) sb.append(' ');
            lines.add(sb.toString());
            i = j;
        }
        return lines.toArray(new String[0]);
    }

    public static String[] justifyTextConcat(String[] words, int width) {
        int i = 0, n = words.length;
        java.util.List<String> lines = new java.util.ArrayList<>();
        while (i < n) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < n && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }
            int gaps = j - i - 1;
            String line = "";
            if (j == n || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line += words[k];
                    if (k < j - 1) line += " ";
                }
                while (line.length() < width) line += " ";
            } else {
                int totalSpaces = width - (lineLen - gaps);
                int spacePerGap = totalSpaces / gaps;
                int extra = totalSpaces % gaps;
                for (int k = i; k < j; k++) {
                    line += words[k];
                    if (k < j - 1) {
                        for (int s = 0; s < spacePerGap; s++) line += " ";
                        if (extra > 0) {
                            line += " ";
                            extra--;
                        }
                    }
                }
            }
            lines.add(line);
            i = j;
        }
        return lines.toArray(new String[0]);
    }

    public static void displayFormattedText(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            System.out.printf("%2d | %s | (%d chars)\n", i + 1, lines[i], lines[i].length());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to format:");
        String text = sc.nextLine();
        System.out.print("Enter desired line width: ");
        int width = sc.nextInt();
        sc.nextLine(); 
        System.out.println("\nOriginal text:");
        System.out.println(text);
        String[] words = splitWords(text);
        long startSB = System.nanoTime();
        String[] justifiedSB = justifyText(words, width);
        long endSB = System.nanoTime();
        long startConcat = System.nanoTime();
        String[] justifiedConcat = justifyTextConcat(words, width);
        long endConcat = System.nanoTime();
        String[] centered = centerAlignText(words, width);
        System.out.println("\nLeft-justified text:");
        displayFormattedText(justifiedSB);
        System.out.println("\nCenter-aligned text:");
        displayFormattedText(centered);
        System.out.println("\nPerformance analysis:");
        System.out.printf("StringBuilder justify: %d ns\n", endSB - startSB);
        System.out.printf("String concatenation justify: %d ns\n", endConcat - startConcat);
        if ((endSB - startSB) < (endConcat - startConcat)) {
            System.out.println("StringBuilder is faster.");
        } else {
            System.out.println("String concatenation is faster.");
        }
    }
}