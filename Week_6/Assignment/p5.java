class BasicMath {
    public int calculate(int a, int b) {
        return a + b;
    }

    public double calculate(double a, double b) {
        return a + b;
    }

    public int calculate(int a, int b, int c) {
        return a + b + c;
    }
}

public class p5 extends BasicMath {
    public double calculate(double a, double b, double c) {
        return a * b * c;
    }

    public int calculate(int a) {
        return a * a;
    }

    public static void main(String[] args) {
        p5 math = new p5();
        System.out.println(math.calculate(5, 3));
        System.out.println(math.calculate(2.5, 3.5));
        System.out.println(math.calculate(1, 2, 3));
        System.out.println(math.calculate(2.0, 3.0, 4.0));
        System.out.println(math.calculate(7));
    }
}
