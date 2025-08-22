import java.util.*;

public class p4 {

    public static boolean isValidExpression(String expr) {
        int paren = 0;
        char prev = ' ';
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (!(c >= '0' && c <= '9') && "+-*/() ".indexOf(c) == -1) return false;
            if (c == '(') paren++;
            if (c == ')') paren--;
            if (paren < 0) return false;
            if ("+-*/".indexOf(c) != -1) {
                if (i == 0 || i == expr.length() - 1) return false;
                if ("+-*/".indexOf(prev) != -1) return false;
            }
            if (c != ' ') prev = c;
        }
        return paren == 0;
    }

    public static List<String> getSteps = new ArrayList<>();

    public static int evaluate(String expr) {
        getSteps.clear();
        getSteps.add(expr);
        while (expr.contains("(")) {
            int close = expr.indexOf(')');
            int open = expr.lastIndexOf('(', close);
            String sub = expr.substring(open + 1, close);
            int val = evaluateSimple(sub);
            expr = expr.substring(0, open) + val + expr.substring(close + 1);
            getSteps.add(expr);
        }
        int result = evaluateSimple(expr);
        getSteps.add(expr + " = " + result);
        return result;
    }

    public static int evaluateSimple(String expr) {
        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        int i = 0, n = expr.length();
        while (i < n) {
            while (i < n && expr.charAt(i) == ' ') i++;
            int start = i;
            while (i < n && expr.charAt(i) >= '0' && expr.charAt(i) <= '9') i++;
            if (start < i) nums.add(Integer.parseInt(expr.substring(start, i)));
            if (i < n && "+-*/".indexOf(expr.charAt(i)) != -1) {
                ops.add(expr.charAt(i));
                i++;
            }
        }
        for (int k = 0; k < ops.size();) {
            if (ops.get(k) == '*' || ops.get(k) == '/') {
                int a = nums.get(k), b = nums.get(k + 1);
                int val = ops.get(k) == '*' ? a * b : a / b;
                nums.set(k, val);
                nums.remove(k + 1);
                ops.remove(k);
                StringBuilder sb = new StringBuilder();
                for (int x = 0; x < nums.size(); x++) {
                    sb.append(nums.get(x));
                    if (x < ops.size()) sb.append(" " + ops.get(x) + " ");
                }
                getSteps.add(sb.toString());
            } else {
                k++;
            }
        }
        for (int k = 0; k < ops.size();) {
            int a = nums.get(k), b = nums.get(k + 1);
            int val = ops.get(k) == '+' ? a + b : a - b;
            nums.set(k, val);
            nums.remove(k + 1);
            ops.remove(k);
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < nums.size(); x++) {
                sb.append(nums.get(x));
                if (x < ops.size()) sb.append(" " + ops.get(x) + " ");
            }
            getSteps.add(sb.toString());
        }
        return nums.get(0);
    }

    public static void displayCalculation(String expr, int result, boolean valid) {
        System.out.println("Original Expression: " + expr);
        if (!valid) {
            System.out.println("Invalid expression format.");
            return;
        }
        System.out.println("Calculation Steps:");
        for (String step : getSteps) System.out.println(step);
        System.out.println("Final Result: " + result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter mathematical expressions (comma separated):");
        String[] exprs = sc.nextLine().split(",");
        for (String expr : exprs) {
            expr = expr.trim();
            boolean valid = isValidExpression(expr);
            int result = valid ? evaluate(expr) : 0;
            displayCalculation(expr, result, valid);
            System.out.println();
        }
    }
}