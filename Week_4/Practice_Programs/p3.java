public class p3 {
    private String mixerModel;
    private int numberOfChannels;
    private boolean hasBluetoothConnectivity;
    private double maxVolumeDecibels;
    private String[] connectedDevices;
    private int deviceCount;

    public p3() {
        this("StandardMix-8", 8, false);
    }

    public p3(String mixerModel, int numberOfChannels) {
        this(mixerModel, numberOfChannels, false);
    }
    public p3(String mixerModel, int numberOfChannels, boolean hasBluetoothConnectivity) {
        this(mixerModel, numberOfChannels, hasBluetoothConnectivity, 120.0);
    }
    public p3(String mixerModel, int numberOfChannels,
                      boolean hasBluetoothConnectivity, double maxVolumeDecibels) {
        this.mixerModel = mixerModel;
        this.numberOfChannels = numberOfChannels;
        this.hasBluetoothConnectivity = hasBluetoothConnectivity;
        this.maxVolumeDecibels = maxVolumeDecibels;
        this.connectedDevices = new String[numberOfChannels];
        this.deviceCount = 0;
        System.out.println("Constructor executed for " + mixerModel);
    }
    public void connectDevice(String deviceName) {
        if (deviceCount < connectedDevices.length) {
            connectedDevices[deviceCount] = deviceName;
            deviceCount++;
            System.out.println("Connected: " + deviceName);
        } else {
            System.out.println("All channels occupied!");
        }
    }
    public void displayMixerStatus() {
        System.out.println("\n=== " + mixerModel + " STATUS ===");
        System.out.println("Channels: " + numberOfChannels);
        System.out.println("Bluetooth: " + (hasBluetoothConnectivity ? "Enabled" : "Disabled"));
        System.out.println("Max Volume: " + maxVolumeDecibels + " dB");
        System.out.println("Connected Devices: " + deviceCount + "/" + numberOfChannels);
        for (int i = 0; i < deviceCount; i++) {
            System.out.println(" Channel " + (i + 1) + ": " + connectedDevices[i]);
        }
    }
    public static void main(String[] args) {
        System.out.println("=== MUSIC STUDIO SETUP ===");
        p3 mixer1 = new p3();
        p3 mixer2 = new p3("DJMix-12", 12);
        p3 mixer3 = new p3("ProMix-16", 16, true);
        p3 mixer4 = new p3("UltraMix-24", 24, true, 150.0);
        mixer1.connectDevice("Guitar");
        mixer1.connectDevice("Keyboard");
        mixer2.connectDevice("Drums");
        mixer3.connectDevice("Microphone");
        mixer3.connectDevice("Laptop");
        mixer4.connectDevice("Synthesizer");
        mixer1.displayMixerStatus();
        mixer2.displayMixerStatus();
        mixer3.displayMixerStatus();
        mixer4.displayMixerStatus();
    }
}
