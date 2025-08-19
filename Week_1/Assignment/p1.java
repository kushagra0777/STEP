import java.util.Scanner;

public class p1 {
    public static String[][] calculateBMI(double[][] heightWeight) {
        String[][] results = new String[heightWeight.length][4];
        for (int i = 0; i < heightWeight.length; i++) {
            double weight = heightWeight[i][0];
            double heightMeters = heightWeight[i][1] / 100;
            double bmi = weight / (heightMeters * heightMeters);
            String status;
            if (bmi < 18.5) status = "Underweight";
            else if (bmi < 25) status = "Normal";
            else if (bmi < 30) status = "Overweight";
            else status = "Obese";
            results[i][0] = String.format("%.2f", heightWeight[i][1]);
            results[i][1] = String.format("%.2f", weight);
            results[i][2] = String.format("%.2f", bmi);
            results[i][3] = status;
        }
        return results;
    }
    public static void displayResults(String[][] results) {
        System.out.printf("%-10s %-10s %-10s %-12s%n", "Height(cm)", "Weight(kg)", "BMI", "Status");
        for (String[] row : results) {
            System.out.printf("%-10s %-10s %-10s %-12s%n", row[0], row[1], row[2], row[3]);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] heightWeight = new double[10][2];
        for (int i = 0; i < 10; i++) {
            System.out.println("Enter details for Person " + (i + 1) + ":");
            System.out.print("Weight (kg): ");
            heightWeight[i][0] = sc.nextDouble();
            System.out.print("Height (cm): ");
            heightWeight[i][1] = sc.nextDouble();
        }
        String[][] results = calculateBMI(heightWeight);
        displayResults(results);
    }
}
