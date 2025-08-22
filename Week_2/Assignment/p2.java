import java.util.*;

public class p2 {

    public static int[] analyzePassword(String pwd) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (int i = 0; i < pwd.length(); i++) {
            char c = pwd.charAt(i);
            if (c >= 65 && c <= 90) upper++;
            else if (c >= 97 && c <= 122) lower++;
            else if (c >= 48 && c <= 57) digit++;
            else if (c >= 33 && c <= 126) special++;
        }
        return new int[]{upper, lower, digit, special};
    }

    public static int calcScore(String pwd, int[] counts) {
        int score = 0;
        int len = pwd.length();
        if (len > 8) score += (len - 8) * 2;
        int types = 0;
        for (int c : counts) if (c > 0) types++;
        score += types * 10;
        String lowerPwd = pwd.toLowerCase();
        String[] patterns = {"123", "abc", "qwerty"};
        for (String p : patterns) if (lowerPwd.contains(p)) score -= 10;
        return score;
    }

    public static String getStrength(int score) {
        if (score <= 20) return "Weak";
        if (score <= 50) return "Medium";
        return "Strong";
    }

    public static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{};:,.<>?";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));
        String all = upper + lower + digits + special;
        for (int i = 4; i < length; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) chars.add(sb.charAt(i));
        Collections.shuffle(chars);
        StringBuilder result = new StringBuilder();
        for (char c : chars) result.append(c);
        return result.toString();
    }

    public static void displayResults(List<String> pwds, List<int[]> counts, List<Integer> scores) {
        System.out.printf("%-18s %-7s %-8s %-8s %-7s %-12s %-7s %-8s\n", "Password", "Length", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        for (int i = 0; i < pwds.size(); i++) {
            String pwd = pwds.get(i);
            int[] c = counts.get(i);
            int score = scores.get(i);
            String strength = getStrength(score);
            System.out.printf("%-18s %-7d %-8d %-8d %-7d %-12d %-7d %-8s\n", pwd, pwd.length(), c[0], c[1], c[2], c[3], score, strength);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> pwds = new ArrayList<>();
        List<int[]> counts = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();
        System.out.println("Enter passwords to analyze (comma separated):");
        String[] inputPwds = sc.nextLine().split(",");
        for (String pwd : inputPwds) {
            pwd = pwd.trim();
            int[] c = analyzePassword(pwd);
            int score = calcScore(pwd, c);
            pwds.add(pwd);
            counts.add(c);
            scores.add(score);
        }
        displayResults(pwds, counts, scores);
        System.out.print("\nGenerate strong password of length: ");
        int len = sc.nextInt();
        String genPwd = generatePassword(len);
        int[] genCounts = analyzePassword(genPwd);
        int genScore = calcScore(genPwd, genCounts);
        System.out.println("\nGenerated Password Analysis:");
        displayResults(Arrays.asList(genPwd), Arrays.asList(genCounts), Arrays.asList(genScore));
    }
}