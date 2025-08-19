import java.util.Scanner;
public class p3 {
    public static long[] concatWithString(int iterations, String sample) {
        long start = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += sample;
        }
        long end = System.currentTimeMillis();
        return new long[]{end - start, result.length()};
    }
    public static long[] concatWithStringBuilder(int iterations, String sample) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(sample);
        }
        long end = System.currentTimeMillis();
        return new long[]{end - start, sb.length()};
    }
    public static long[] concatWithStringBuffer(int iterations, String sample) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append(sample);
        }
        long end = System.currentTimeMillis();
        return new long[]{end - start, sb.length()};
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of iterations: ");
        int iterations = sc.nextInt();
        String sample = "abc";

        long[] stringRes = concatWithString(iterations, sample);
        long[] builderRes = concatWithStringBuilder(iterations, sample);
        long[] bufferRes = concatWithStringBuffer(iterations, sample);

        System.out.println("\n Method         , Time (ms) , Final Length ");
        System.out.printf(" String (+)     , %-9d , %-12d \n", stringRes[0], stringRes[1]);
        System.out.printf(" StringBuilder  , %-9d , %-12d \n", builderRes[0], builderRes[1]);
        System.out.printf(" StringBuffer   , %-9d , %-12d \n", bufferRes[0], bufferRes[1]);
    }
}