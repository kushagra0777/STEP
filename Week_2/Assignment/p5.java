import java.util.*;
public class p5 {
    public static String[][] parseCSV(String input) {
        List<List<String>> rows = new ArrayList<>();
        int i = 0, n = input.length();
        while (i < n) {
            List<String> row = new ArrayList<>();
            while (i < n && (input.charAt(i) == '\n' || input.charAt(i) == '\r')) i++;
            while (i < n && input.charAt(i) != '\n' && input.charAt(i) != '\r') {
                StringBuilder field = new StringBuilder();
                boolean quoted = false;
                if (input.charAt(i) == '"') {
                    quoted = true;
                    i++;
                }
                while (i < n) {
                    if (quoted) {
                        if (input.charAt(i) == '"') {
                            if (i + 1 < n && input.charAt(i + 1) == '"') {
                                field.append('"');
                                i += 2;
                            } else {
                                i++;
                                break;
                            }
                        } else {
                            field.append(input.charAt(i++));
                        }
                    } else if (input.charAt(i) == ',') {
                        i++;
                        break;
                    } else if (input.charAt(i) == '\n' || input.charAt(i) == '\r') {
                        break;
                    } else {
                        field.append(input.charAt(i++));
                    }
                }
                row.add(field.toString());
                while (i < n && input.charAt(i) == ' ') i++;
            }
            if (!row.isEmpty()) rows.add(row);
            while (i < n && (input.charAt(i) == '\n' || input.charAt(i) == '\r')) i++;
        }
        int cols = rows.isEmpty() ? 0 : rows.get(0).size();
        String[][] data = new String[rows.size()][cols];
        for (int r = 0; r < rows.size(); r++)
            for (int c = 0; c < cols; c++)
                data[r][c] = rows.get(r).get(c);
        return data;
    }

    public static String[][] cleanAndValidate(String[][] data, boolean[] isNumeric, List<String> issues) {
        int rows = data.length, cols = data[0].length;
        String[][] cleaned = new String[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                String field = data[r][c].trim();
                if (field.isEmpty()) {
                    issues.add("Row " + (r + 1) + ", Col " + (c + 1) + ": Missing value");
                }
                if (isNumeric[c]) {
                    boolean valid = true;
                    for (int i = 0; i < field.length(); i++) {
                        char ch = field.charAt(i);
                        if (!(ch >= '0' && ch <= '9') && ch != '.' && ch != '-') valid = false;
                    }
                    if (!valid || field.isEmpty()) {
                        issues.add("Row " + (r + 1) + ", Col " + (c + 1) + ": Invalid numeric value '" + field + "'");
                        field = "";
                    }
                }
                cleaned[r][c] = field;
            }
        }
        return cleaned;
    }

    public static Map<Integer, double[]> analyzeNumericColumns(String[][] data, boolean[] isNumeric) {
        Map<Integer, double[]> stats = new HashMap<>();
        int rows = data.length;
        for (int c = 0; c < isNumeric.length; c++) {
            if (isNumeric[c]) {
                double min = Double.MAX_VALUE, max = -Double.MAX_VALUE, sum = 0;
                int count = 0;
                for (int r = 1; r < rows; r++) {
                    String val = data[r][c];
                    if (!val.isEmpty()) {
                        double num = Double.parseDouble(val);
                        min = Math.min(min, num);
                        max = Math.max(max, num);
                        sum += num;
                        count++;
                    }
                }
                double avg = count > 0 ? sum / count : 0;
                stats.put(c, new double[]{min, max, avg, count});
            }
        }
        return stats;
    }

    public static Map<Integer, Set<String>> analyzeCategoricalColumns(String[][] data, boolean[] isNumeric) {
        Map<Integer, Set<String>> uniques = new HashMap<>();
        int rows = data.length;
        for (int c = 0; c < isNumeric.length; c++) {
            if (!isNumeric[c]) {
                Set<String> set = new HashSet<>();
                for (int r = 1; r < rows; r++) {
                    String val = data[r][c];
                    if (!val.isEmpty()) set.add(val);
                }
                uniques.put(c, set);
            }
        }
        return uniques;
    }

    public static String formatTable(String[][] data, boolean[] isNumeric, List<String> issues) {
        int rows = data.length, cols = data[0].length;
        int[] widths = new int[cols];
        for (int c = 0; c < cols; c++) {
            widths[c] = data[0][c].length();
            for (int r = 1; r < rows; r++) {
                int len = data[r][c].length();
                if (len > widths[c]) widths[c] = len;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int w : widths) {
            for (int i = 0; i < w + 2; i++) sb.append("-");
            sb.append("+");
        }
        sb.append("\n|");
        for (int c = 0; c < cols; c++) {
            sb.append(" ").append(data[0][c]);
            for (int i = data[0][c].length(); i < widths[c]; i++) sb.append(" ");
            sb.append(" |");
        }
        sb.append("\n+");
        for (int w : widths) {
            for (int i = 0; i < w + 2; i++) sb.append("-");
            sb.append("+");
        }
        sb.append("\n");
        for (int r = 1; r < rows; r++) {
            sb.append("|");
            for (int c = 0; c < cols; c++) {
                String val = data[r][c];
                if (val.isEmpty()) sb.append(" ").append("**MISSING**");
                else if (isNumeric[c]) {
                    try {
                        double d = Double.parseDouble(val);
                        sb.append(String.format(" %,.2f", d));
                        for (int i = String.format("%,.2f", d).length(); i < widths[c]; i++) sb.append(" ");
                    } catch (Exception e) {
                        sb.append(" ").append(val);
                        for (int i = val.length(); i < widths[c]; i++) sb.append(" ");
                    }
                } else {
                    sb.append(" ").append(val);
                    for (int i = val.length(); i < widths[c]; i++) sb.append(" ");
                }
                sb.append(" |");
            }
            sb.append("\n");
        }
        sb.append("+");
        for (int w : widths) {
            for (int i = 0; i < w + 2; i++) sb.append("-");
            sb.append("+");
        }
        sb.append("\n");
        if (!issues.isEmpty()) {
            sb.append("Data Quality Issues:\n");
            for (String issue : issues) sb.append(" - ").append(issue).append("\n");
        }
        return sb.toString();
    }

    public static void displaySummary(String[][] data, boolean[] isNumeric, Map<Integer, double[]> stats, Map<Integer, Set<String>> uniques, List<String> issues) {
        int total = data.length - 1, missing = 0, cols = data[0].length;
        for (int r = 1; r < data.length; r++)
            for (int c = 0; c < cols; c++)
                if (data[r][c].isEmpty()) missing++;
        double completeness = total * cols > 0 ? 100.0 * (total * cols - missing) / (total * cols) : 0;
        System.out.println("Summary Report:");
        System.out.println("Total records processed: " + total);
        for (int c = 0; c < cols; c++) {
            System.out.print("Column " + (c + 1) + " (" + data[0][c] + "): ");
            if (isNumeric[c] && stats.containsKey(c)) {
                double[] s = stats.get(c);
                System.out.printf("Min=%.2f, Max=%.2f, Avg=%.2f, Count=%d\n", s[0], s[1], s[2], (int)s[3]);
            } else if (!isNumeric[c] && uniques.containsKey(c)) {
                System.out.println("Unique values: " + uniques.get(c));
            }
        }
        System.out.println("Data completeness: " + String.format("%.2f", completeness) + "%");
        if (!issues.isEmpty()) {
            System.out.println("Data Quality Findings:");
            for (String issue : issues) System.out.println(" - " + issue);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end input with an empty line):");
        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) break;
            input.append(line).append("\n");
        }
        String[][] raw = parseCSV(input.toString());
        int cols = raw[0].length;
        boolean[] isNumeric = new boolean[cols];
        for (int c = 0; c < cols; c++) {
            boolean numeric = true;
            for (int r = 1; r < raw.length; r++) {
                String val = raw[r][c].trim();
                if (val.isEmpty()) continue;
                for (int i = 0; i < val.length(); i++) {
                    char ch = val.charAt(i);
                    if (!(ch >= '0' && ch <= '9') && ch != '.' && ch != '-') numeric = false;
                }
                if (!numeric) break;
            }
            isNumeric[c] = numeric;
        }
        List<String> issues = new ArrayList<>();
        String[][] cleaned = cleanAndValidate(raw, isNumeric, issues);
        Map<Integer, double[]> stats = analyzeNumericColumns(cleaned, isNumeric);
        Map<Integer, Set<String>> uniques = analyzeCategoricalColumns(cleaned, isNumeric);
        String table = formatTable(cleaned, isNumeric, issues);
        System.out.println(table);
        displaySummary(cleaned, isNumeric, stats, uniques, issues);
    }
}