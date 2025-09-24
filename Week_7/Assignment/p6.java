class SmartDevice {
    void control() { System.out.println("Generic smart device control"); }
}
class SmartTV extends SmartDevice {
    void tvControl() { System.out.println("Smart TV: manage channels, volume, streaming apps"); }
}
class SmartThermostat extends SmartDevice {
    void thermoControl() { System.out.println("Smart Thermostat: control temperature, humidity, energy modes"); }
}
class SmartSecurity extends SmartDevice {
    void securityControl() { System.out.println("Smart Security: handle cameras, alarms, access controls"); }
}
class SmartKitchen extends SmartDevice {
    void kitchenControl() { System.out.println("Smart Kitchen: manage cooking times, temperature, recipes"); }
}
public class p6 {
    public static void main(String[] args) {
        SmartDevice[] devices = { new SmartTV(), new SmartThermostat(), new SmartSecurity(), new SmartKitchen() };
        for (SmartDevice d : devices) {
            if (d instanceof SmartTV) ((SmartTV)d).tvControl();
            else if (d instanceof SmartThermostat) ((SmartThermostat)d).thermoControl();
            else if (d instanceof SmartSecurity) ((SmartSecurity)d).securityControl();
            else if (d instanceof SmartKitchen) ((SmartKitchen)d).kitchenControl();
        }
    }
}
