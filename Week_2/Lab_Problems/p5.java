import java.util.*;
public class p5 {
    static class EmailInfo {
        String email, username, domain, domainName, extension;
        boolean valid;
        EmailInfo(String email, String username, String domain, String domainName, String extension, boolean valid) {
            this.email = email;
            this.username = username;
            this.domain = domain;
            this.domainName = domainName;
            this.extension = extension;
            this.valid = valid;
        }
    }

    public static boolean isValidEmail(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (at <= 0 || at != lastAt) return false;
        int dot = email.indexOf('.', at);
        if (dot == -1 || dot == at + 1 || dot == email.length() - 1) return false;
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }

    public static EmailInfo extractInfo(String email) {
        boolean valid = isValidEmail(email);
        String username = "", domain = "", domainName = "", extension = "";
        if (valid) {
            int at = email.indexOf('@');
            int dot = email.indexOf('.', at);
            username = email.substring(0, at);
            domain = email.substring(at + 1);
            domainName = domain.substring(0, dot - at - 1);
            extension = domain.substring(dot - at);
        }
        return new EmailInfo(email, username, domain, domainName, extension, valid);
    }

    public static void analyze(List<EmailInfo> infos) {
        int validCount = 0, invalidCount = 0, totalUsernameLen = 0;
        Map<String, Integer> domainCount = new HashMap<>();
        for (EmailInfo info : infos) {
            if (info.valid) {
                validCount++;
                totalUsernameLen += info.username.length();
                domainCount.put(info.domainName, domainCount.getOrDefault(info.domainName, 0) + 1);
            } else {
                invalidCount++;
            }
        }
        String mostCommonDomain = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> e : domainCount.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                mostCommonDomain = e.getKey();
            }
        }
        double avgUsernameLen = validCount == 0 ? 0 : (double)totalUsernameLen / validCount;
        System.out.println("\nStatistics:");
        System.out.println("Total Valid Emails: " + validCount);
        System.out.println("Total Invalid Emails: " + invalidCount);
        System.out.println("Most Common Domain: " + mostCommonDomain);
        System.out.printf("Average Username Length: %.2f\n", avgUsernameLen);
    }

    public static void displayTable(List<EmailInfo> infos) {
        System.out.println("\n| Email                | Username   | Domain         | Domain Name | Extension | Valid |");
        for (EmailInfo info : infos) {
            System.out.printf("| %-20s | %-10s | %-13s | %-10s | %-8s | %-5s |\n",
                info.email, info.username, info.domain, info.domainName, info.extension, info.valid ? "Yes" : "No");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<EmailInfo> infos = new ArrayList<>();
        System.out.println("Enter email addresses (type 'end' to finish):");
        while (true) {
            String email = sc.nextLine();
            if (email.equalsIgnoreCase("end")) break;
            infos.add(extractInfo(email));
        }
        displayTable(infos);
        analyze(infos);
    }
}