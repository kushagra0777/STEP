import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class p2 {
    private final String deviceId;
    private final LocalDateTime manufacturingDate;
    private final String serialNumber;
    private int hashedEncryptionKey;
    private int hashedAdminPassword;
    private String deviceName;
    private boolean isEnabled;
    private final LocalDateTime startupTime;

    public p2(String deviceName) {
        this.deviceId = UUID.randomUUID().toString();
        this.manufacturingDate = LocalDateTime.now().minusDays((long) (Math.random() * 1000));
        this.serialNumber = "SN-" + UUID.randomUUID().toString().substring(0, 8);
        this.startupTime = LocalDateTime.now();
        this.deviceName = deviceName;
        this.isEnabled = true;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getManufacturingDate() {
        return manufacturingDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public long getUptime() {
        return Duration.between(startupTime, LocalDateTime.now()).getSeconds();
    }

    public int getDeviceAge() {
        return LocalDateTime.now().getYear() - manufacturingDate.getYear();
    }

    public void setEncryptionKey(String key) {
        if (key.length() < 8) throw new IllegalArgumentException("Encryption key too short");
        this.hashedEncryptionKey = key.hashCode();
    }

    public void setAdminPassword(String password) {
        if (password.length() < 6) throw new IllegalArgumentException("Password too weak");
        this.hashedAdminPassword = password.hashCode();
    }

    public boolean validateEncryptionKey(String key) {
        return this.hashedEncryptionKey == key.hashCode();
    }

    public boolean validateAdminPassword(String password) {
        return this.hashedAdminPassword == password.hashCode();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String name) {
        this.deviceName = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }

    public Map<String, String> getPropertyInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("deviceId", "Read-only");
        info.put("manufacturingDate", "Read-only");
        info.put("serialNumber", "Read-only");
        info.put("uptime", "Computed Read-only");
        info.put("deviceAge", "Computed Read-only");
        info.put("encryptionKey", "Write-only");
        info.put("adminPassword", "Write-only");
        info.put("deviceName", "Read-write");
        info.put("isEnabled", "Read-write");
        return info;
    }

    public void resetDevice() {
        this.hashedEncryptionKey = 0;
        this.hashedAdminPassword = 0;
        this.isEnabled = false;
        this.deviceName = "ResetDevice";
    }

    public static void main(String[] args) {
        p2 device1 = new p2("Router");
        p2 device2 = new p2("Camera");

        System.out.println("--- Read-only properties ---");
        System.out.println("Device1 ID: " + device1.getDeviceId());
        System.out.println("Manufactured: " + device1.getManufacturingDate());
        System.out.println("Serial: " + device1.getSerialNumber());
        System.out.println("Uptime: " + device1.getUptime() + " sec");
        System.out.println("Device age: " + device1.getDeviceAge() + " years");

        System.out.println("\n--- Write-only properties ---");
        device1.setEncryptionKey("SuperSecretKey123");
        device1.setAdminPassword("Admin@123");
        System.out.println("Validate key: " + device1.validateEncryptionKey("SuperSecretKey123"));
        System.out.println("Validate password: " + device1.validateAdminPassword("Admin@123"));

        System.out.println("\n--- Read-write properties ---");
        System.out.println("Device name: " + device1.getDeviceName());
        device1.setDeviceName("Home Router");
        System.out.println("New name: " + device1.getDeviceName());
        System.out.println("Enabled: " + device1.isEnabled());
        device1.setEnabled(false);
        System.out.println("Enabled after disable: " + device1.isEnabled());

        System.out.println("\n--- Multiple devices independence ---");
        System.out.println("Device2 name: " + device2.getDeviceName());
        System.out.println("Device2 ID: " + device2.getDeviceId());

        System.out.println("\n--- Property Info ---");
        System.out.println(device1.getPropertyInfo());

        System.out.println("\n--- Reset device1 ---");
        device1.resetDevice();
        System.out.println("Device1 name after reset: " + device1.getDeviceName());
        System.out.println("Device1 enabled: " + device1.isEnabled());
    }
}
