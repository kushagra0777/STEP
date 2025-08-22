import java.util.*;

public class p3 {

    public static Object[] countCharFrequency(String text) {
        char[] chars = new char[text.length()];
        int[] freq = new int[text.length()];
        int unique = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int idx = -1;
            for (int j = 0; j < unique; j++) {
                if (chars[j] == c) {
                    idx = j;
                    break;
                }
            }
            if (idx == -1) {
                chars[unique] = c;
                freq[unique] = 1;
                unique++;
            } else {
                freq[idx]++;
            }
        }
        char[] resultChars = new char[unique];
        int[] resultFreq = new int[unique];
        System.arraycopy(chars, 0, resultChars, 0, unique);
        System.arraycopy(freq, 0, resultFreq, 0, unique);
        return new Object[]{resultChars, resultFreq};
    }

    public static String[][] createCompressionCodes(char[] chars, int[] freq) {
        int n = chars.length;
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (freq[idx[j]] > freq[idx[i]]) {
                    int tmp = idx[i]; idx[i] = idx[j]; idx[j] = tmp;
                }
            }
        }
        String[] codes = new String[n];
        String symbols = "0123456789!@#$%^&*abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < n; i++) {
            if (i < symbols.length()) codes[idx[i]] = "" + symbols.charAt(i);
            else codes[idx[i]] = "{" + i + "}";
        }
        String[][] mapping = new String[n][2];
        for (int i = 0; i < n; i++) {
            mapping[i][0] = "" + chars[i];
            mapping[i][1] = codes[i];
        }
        return mapping;
    }

    public static String compressText(String text, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String code = "";
            for (String[] map : mapping) {
                if (map[0].charAt(0) == text.charAt(i)) {
                    code = map[1];
                    break;
                }
            }
            sb.append(code);
        }
        return sb.toString();
    }

    public static String decompressText(String compressed, String[][] mapping, String original) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < compressed.length()) {
            boolean found = false;
            for (String[] map : mapping) {
                String code = map[1];
                if (compressed.startsWith(code, i)) {
                    sb.append(map[0]);
                    i += code.length();
                    found = true;
                    break;
                }
            }
            if (!found) i++;
        }
        return sb.toString();
    }

    public static void displayAnalysis(char[] chars, int[] freq, String[][] mapping, String original, String compressed, String decompressed) {
        System.out.println("Character Frequency Table:");
        for (int i = 0; i < chars.length; i++) {
            System.out.printf("'%c' : %d\n", chars[i], freq[i]);
        }
        System.out.println("\nCompression Mapping:");
        for (String[] map : mapping) {
            System.out.printf("'%s' -> '%s'\n", map[0], map[1]);
        }
        System.out.println("\nOriginal Text:\n" + original);
        System.out.println("\nCompressed Text:\n" + compressed);
        System.out.println("\nDecompressed Text:\n" + decompressed);
        int origSize = original.length();
        int compSize = compressed.length();
        double efficiency = (1.0 - (double)compSize / origSize) * 100;
        System.out.printf("\nCompression Efficiency: %.2f%%\n", efficiency);
        System.out.println("Decompression " + (original.equals(decompressed) ? "successful." : "failed."));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String text = sc.nextLine();
        Object[] freqData = countCharFrequency(text);
        char[] chars = (char[]) freqData[0];
        int[] freq = (int[]) freqData[1];
        String[][] mapping = createCompressionCodes(chars, freq);
        String compressed = compressText(text, mapping);
        String decompressed = decompressText(compressed, mapping, text);
        displayAnalysis(chars, freq, mapping, text, compressed, decompressed);
    }
}