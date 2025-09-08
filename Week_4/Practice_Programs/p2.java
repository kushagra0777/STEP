import Week_4.Lab_Programs.p2;

public class p2 {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;
    public p2() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }
    public p2(String controllerBrand, String connectionType,
                          boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        if (batteryLevel < 0) batteryLevel = 0;
        if (batteryLevel > 100) batteryLevel = 100;
        this.batteryLevel = batteryLevel;
        if (sensitivity < 0.1) sensitivity = 0.1;
        if (sensitivity > 3.0) sensitivity = 3.0;
        this.sensitivity = sensitivity;
    }
    public p2(String brand, String connectionType) {
        this(brand, connectionType, true, 100, 1.0);
    }
    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }
    public void displayConfiguration() {
        System.out.println("\n=== CONTROLLER CONFIGURATION ===");
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
    }
    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }
    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===");
        p2 defaultCtrl = new p2();
        p2 customCtrl = new p2("ProPad", "Wireless", false, 80, 2.5);
        p2 convenienceCtrl = new p2("LitePad", "USB");
        defaultCtrl.displayConfiguration();
        defaultCtrl.calibrateController();
        defaultCtrl.testVibration();
        customCtrl.displayConfiguration();
        customCtrl.calibrateController();
        customCtrl.testVibration();
        convenienceCtrl.displayConfiguration();
        convenienceCtrl.calibrateController();
        convenienceCtrl.testVibration();
    }
}