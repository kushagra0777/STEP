public class p4 {
    private String deviceName;
    private String location;
    private boolean isOnline;
    private double powerConsumption;
    private String[] connectedDevices;
    private int connectionCount;

    public p4(String deviceName, String location, boolean isOnline, double powerConsumption) {
        this.deviceName = deviceName;
        this.location = location;
        this.isOnline = isOnline;
        this.powerConsumption = powerConsumption;
        this.connectedDevices = new String[5];
        this.connectionCount = 0;
    }

    public void updateLocation(String location) {
        this.location = location;
        System.out.println(this.deviceName + " moved to " + this.location);
    }

    public void updatePowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
        System.out.println("Power consumption updated for " + this.deviceName);
    }

    public p4 setOnline(boolean isOnline) {
        this.isOnline = isOnline;
        return this;
    }

    public p4 connectToDevice(String deviceName) {
        if (this.connectionCount < this.connectedDevices.length) {
            this.connectedDevices[this.connectionCount] = deviceName;
            this.connectionCount++;
            System.out.println(this.deviceName + " connected to " + deviceName);
        }
        return this;
    }

    public p4 rename(String deviceName) {
        String oldName = this.deviceName;
        this.deviceName = deviceName;
        System.out.println("Device renamed from " + oldName + " to " + this.deviceName);
        return this;
    }

    public void displayDeviceInfo() {
        System.out.println("\n=== " + this.deviceName + " INFO ===");
        System.out.println("Location: " + this.location);
        System.out.println("Status: " + (this.isOnline ? "Online" : "Offline"));
        System.out.println("Power: " + this.powerConsumption + "W");
        System.out.println("Connections: " + this.connectionCount);
        for (int i = 0; i < this.connectionCount; i++) {
            System.out.println(" -> " + this.connectedDevices[i]);
        }
    }

    public void performInitialSetup() {
        this.setOnline(true);
        System.out.println(this.deviceName + " initial setup completed");
    }

    public static void main(String[] args) {
        System.out.println("=== SMART HOME DEVICE NETWORK ===");
        p4 device1 = new p4("LightBulb", "Living Room", false, 10.5);
        p4 device2 = new p4("Thermostat", "Bedroom", true, 5.0);
        device1.setOnline(true).connectToDevice("Alexa").rename("Main Light");
        device2.connectToDevice("Google Home").connectToDevice("AC Unit");
        device1.displayDeviceInfo();
        device2.displayDeviceInfo();
        device1.performInitialSetup();
    }
}
