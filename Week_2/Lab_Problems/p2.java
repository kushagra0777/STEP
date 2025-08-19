import java.util.*;
public class p2 {
    public static char toUpper(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return (char)(ch - 32);
        }
        return ch;
    }
    public static char toLower(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return (char)(ch + 32);
        }
        return ch;
    }
    public static String toUpperCaseManual(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(toUpper(text.charAt(i)));
        }
        return sb.toString();
    }
    public static String toLowerCaseManual(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(toLower(text.charAt(i)));
        }
        return sb.toString();
    }
    public static String toTitleCaseManual(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                sb.append(ch);
                newWord = true;
            } else if (newWord) {
                sb.append(toUpper(ch));
                newWord = false;
            } else {
                sb.append(toLower(ch));
            }
        }
        return sb.toString();
    }
    public static boolean compareWithBuiltIn(String manual, String builtin) {
        return manual.equals(builtin);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        String manualUpper = toUpperCaseManual(text);
        String manualLower = toLowerCaseManual(text);
        String manualTitle = toTitleCaseManual(text);
        String builtinUpper = text.toUpperCase();
        String builtinLower = text.toLowerCase();
        System.out.println("\n   Conversion    ,  Manual Result           , Built-in Result         , Match? ");
        System.out.printf(" Uppercase     , %-22s , %-23s , %-6s \n", manualUpper, builtinUpper, compareWithBuiltIn(manualUpper, builtinUpper));
        System.out.printf(" Lowercase     , %-22s , %-23s , %-6s \n", manualLower, builtinLower, compareWithBuiltIn(manualLower, builtinLower));
        System.out.printf(" Title Case    , %-22s , %-23s , %-6s \n", manualTitle, "N/A", "N/A");
    }
}