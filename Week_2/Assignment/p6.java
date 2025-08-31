import java.util.*;
public class p6 {
    static class FileInfo {
        String originalName;
        String baseName;
        String extension;
        String category;
        String subcategory;
        String newName;
        boolean valid;
        int priority;

        FileInfo(String originalName, String baseName, String extension, boolean valid) {
            this.originalName = originalName;
            this.baseName = baseName;
            this.extension = extension;
            this.valid = valid;
        }
    }
    static Map<String, String> extCategory = new HashMap<>();
    static {
        extCategory.put("txt", "Document");
        extCategory.put("doc", "Document");
        extCategory.put("pdf", "Document");
        extCategory.put("jpg", "Image");
        extCategory.put("png", "Image");
        extCategory.put("gif", "Image");
        extCategory.put("mp3", "Audio");
        extCategory.put("wav", "Audio");
        extCategory.put("mp4", "Video");
        extCategory.put("avi", "Video");
        extCategory.put("zip", "Archive");
        extCategory.put("rar", "Archive");
        extCategory.put("exe", "Executable");
        extCategory.put("py", "Code");
        extCategory.put("java", "Code");
        extCategory.put("cpp", "Code");
    }

    public static FileInfo extractFileInfo(String filename) {
        int dot = filename.lastIndexOf('.');
        boolean valid = dot > 0 && dot < filename.length() - 1;
        String base = valid ? filename.substring(0, dot) : filename;
        String ext = valid ? filename.substring(dot + 1).toLowerCase() : "";
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            if (!(c >= 48 && c <= 57) && !(c >= 65 && c <= 90) && !(c >= 97 && c <= 122) && c != '_' && c != '-') valid = false;
        }
        return new FileInfo(filename, base, ext, valid);
    }

    public static void categorizeFiles(List<FileInfo> files) {
        for (FileInfo f : files) {
            f.category = extCategory.getOrDefault(f.extension, "Unknown");
        }
    }

    public static void generateNewNames(List<FileInfo> files) {
        Map<String, Integer> nameCount = new HashMap<>();
        String date = java.time.LocalDate.now().toString().replace("-", "");
        for (FileInfo f : files) {
            StringBuilder sb = new StringBuilder();
            sb.append(f.category);
            sb.append("_").append(date);
            sb.append("_").append(f.baseName);
            int count = nameCount.getOrDefault(sb.toString(), 0) + 1;
            nameCount.put(sb.toString(), count);
            if (count > 1) sb.append("_").append(count);
            sb.append(".").append(f.extension);
            f.newName = sb.toString();
            for (int i = 0; i < f.newName.length(); i++) {
                char c = f.newName.charAt(i);
                if (!(c >= 48 && c <= 57) && !(c >= 65 && c <= 90) && !(c >= 97 && c <= 122) && c != '_' && c != '-' && c != '.' ) {
                    f.valid = false;
                    break;
                }
            }
        }
    }

    public static void analyzeContent(List<FileInfo> files, Map<String, String> contentMap) {
        for (FileInfo f : files) {
            if (f.extension.equals("txt") || f.extension.equals("doc")) {
                String content = contentMap.getOrDefault(f.originalName, "");
                String lc = content.toLowerCase();
                if (lc.contains("resume")) f.subcategory = "Resume";
                else if (lc.contains("report")) f.subcategory = "Report";
                else if (lc.contains("code") || lc.contains("class") || lc.contains("public")) f.subcategory = "Code";
                else f.subcategory = "General";
                int priority = 0;
                if (f.baseName.toLowerCase().contains("urgent") || lc.contains("urgent")) priority += 2;
                if (f.subcategory.equals("Resume")) priority += 1;
                f.priority = priority;
                for (int i = 0; i < content.length(); i++) {
                    char c = content.charAt(i);
                    if (c < 32 || c > 126) f.valid = false;
                }
            } else {
                f.subcategory = "-";
                f.priority = 0;
            }
        }
    }

    public static void displayReport(List<FileInfo> files) {
        Map<String, Integer> catCount = new HashMap<>();
        List<FileInfo> needsAttention = new ArrayList<>();
        System.out.printf("%-25s %-12s %-12s %-20s %-10s %-8s\n", "Original Name", "Category", "Subcategory", "New Name", "Valid", "Priority");
        for (FileInfo f : files) {
            System.out.printf("%-25s %-12s %-12s %-20s %-10s %-8d\n", f.originalName, f.category, f.subcategory, f.newName, f.valid ? "Yes" : "No", f.priority);
            catCount.put(f.category, catCount.getOrDefault(f.category, 0) + 1);
            if (!f.valid || f.category.equals("Unknown")) needsAttention.add(f);
        }
        System.out.println("\nCategory-wise File Counts:");
        for (String cat : catCount.keySet()) {
            System.out.printf("%-12s : %d\n", cat, catCount.get(cat));
        }
        if (!needsAttention.isEmpty()) {
            System.out.println("\nFiles needing attention:");
            for (FileInfo f : needsAttention) {
                System.out.println(f.originalName + " (" + (f.valid ? "" : "Invalid name") + (f.category.equals("Unknown") ? " Unknown type" : "") + ")");
            }
        }
        System.out.println("\nTotal files: " + files.size());
        System.out.println("Valid files: " + (files.size() - needsAttention.size()));
        System.out.println("Unknown types: " + (int) files.stream().filter(f -> f.category.equals("Unknown")).count());
    }

    public static void batchRenameCommands(List<FileInfo> files) {
        System.out.println("\nBatch Rename Commands:");
        for (FileInfo f : files) {
            if (f.valid && !f.originalName.equals(f.newName)) {
                System.out.println("mv \"" + f.originalName + "\" \"" + f.newName + "\"");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();
        Map<String, String> contentMap = new HashMap<>();
        System.out.println("Enter file names (comma separated):");
        String[] names = sc.nextLine().split(",");
        for (String name : names) {
            name = name.trim();
            FileInfo info = extractFileInfo(name);
            files.add(info);
        }
        for (FileInfo f : files) {
            if (f.extension.equals("txt") || f.extension.equals("doc")) {
                System.out.print("Enter content for " + f.originalName + ": ");
                String content = sc.nextLine();
                contentMap.put(f.originalName, content);
            }
        }
        categorizeFiles(files);
        generateNewNames(files);
        analyzeContent(files, contentMap);
        displayReport(files);
    }
}