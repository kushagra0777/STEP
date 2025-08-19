import java.util.*;

public class p1 {
    public static List<Integer> findOccurrences(String text, String find) {
        List<Integer> positions = new ArrayList<>();
        int in = text.indexOf(find);
        while (in != -1) {
            positions.add(in);
            in = text.indexOf(find, in + find.length());
        }
        return positions;
    }
    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - find.length() && text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);
                i += find.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }
    public static boolean compareWithBuiltIn(String manual, String builtin) {
        return manual.equals(builtin);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter main text: ");
        String text = sc.nextLine();
        System.out.print("Enter substring to find: ");
        String find = sc.nextLine();
        System.out.print("Enter substring to replace: ");
        String replace = sc.nextLine();
        List<Integer> positions = findOccurrences(text, find);
        System.out.println("Occurrences at positions: " + positions);
        String manualResult = manualReplace(text, find, replace);
        String builtinResult = text.replace(find, replace);
        System.out.println("Manual Replace: " + manualResult);
        System.out.println("Built-in Replace: " + builtinResult);
        boolean isSame = compareWithBuiltIn(manualResult, builtinResult);
        System.out.println("Results are same? " + isSame);
    }
}