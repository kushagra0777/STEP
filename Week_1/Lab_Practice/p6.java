
import java.util.Scanner;

public class p6 {

    public static String checkCharType(char ch) {
        if (ch >= 'A' && ch <= 'Z') ch = (char)(ch + 32); 
        if (ch >= 'a' && ch <= 'z') {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                return "Vowel";
            else
                return "Consonant";
        }
        return "Not a Letter";
    }

    public static String[][] charTypeTable(String str) {
        int len = 0;
        try {
            while (true) { str.charAt(len); len++; }
        } catch (StringIndexOutOfBoundsException e) {}

        String[][] table = new String[len][2];
        for (int i = 0; i < len; i++) {
            table[i][0] = String.valueOf(str.charAt(i));
            table[i][1] = checkCharType(str.charAt(i));
        }
        return table;
    }

    public static void displayTable(String[][] data) {
        System.out.printf("%-10s %-15s%n", "Character", "Type");
        for (String[] row : data) {
            System.out.printf("%-10s %-15s%n", row[0], row[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[][] table = charTypeTable(input);
        displayTable(table);
    }
}
