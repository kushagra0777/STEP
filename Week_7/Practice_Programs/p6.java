class SmartDevice {
    protected String name;

    public SmartDevice(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " is turned ON");
    }

    public void turnOff() {
        System.out.println(name + " is turned OFF");
    }
}

class SmartLight extends SmartDevice {
    public SmartLight(String name) {
        super(name);
    }

    public void setBrightness(int level) {
        System.out.println(name + " brightness set to " + level + "%");
    }
}

class SmartThermostat extends SmartDevice {
    public SmartThermostat(String name) {
        super(name);
    }

    public void setTemperature(int temp) {
        System.out.println(name + " temperature set to " + temp + "°C");
    }
}

public class p6 {
    public static void main(String[] args) {
        SmartDevice d1 = new SmartLight("Living Room Light");
        SmartDevice d2 = new SmartThermostat("Hall Thermostat");

        if (d1 instanceof SmartLight) {
            SmartLight light = (SmartLight) d1;
            light.turnOn();
            light.setBrightness(80);
        }

        if (d2 instanceof SmartThermostat) {
            SmartThermostat thermostat = (SmartThermostat) d2;
            thermostat.turnOn();
            thermostat.setTemperature(24);
        }
    }
}
