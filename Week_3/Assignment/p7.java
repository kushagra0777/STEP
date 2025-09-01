import java.util.*;

class Driver {
    final String driverId;
    final String driverName;
    final String licenseType;
    String assignedVehicle;
    int totalTrips=0;
    public Driver(String driverId,String driverName,String licenseType){ this.driverId=driverId; this.driverName=driverName; this.licenseType=licenseType; }
}

class VehicleBase {
    private static int seq=0;
    static int totalVehicles=0;
    static double fleetValue=0;
    static String companyName="Unset";
    static double totalFuelConsumption=0;

    final String vehicleId;
    final String brand;
    final String model;
    final int year;
    double mileage;
    final String fuelType;
    String currentStatus="Idle";
    double runningCost=0;
    double value;

    public VehicleBase(String brand,String model,int year,double mileage,String fuelType,double value){
        this.vehicleId=String.format("V%04d", ++seq);
        this.brand=brand; this.model=model; this.year=year; this.mileage=mileage; this.fuelType=fuelType; this.value=value;
        totalVehicles++; fleetValue+=value;
    }

    public void assignDriver(Driver d){ d.assignedVehicle=vehicleId; }
    public void scheduleMaintenance(){ currentStatus="Maintenance"; }
    public void updateMileage(double km){ mileage+=km; }
    public void checkServiceDue(){}
    public double calculateRunningCost(double fuelLiters, double fuelPrice, double other){
        totalFuelConsumption += fuelLiters;
        runningCost += fuelLiters*fuelPrice + other;
        return runningCost;
    }
}

class CarV extends VehicleBase { int seats; public CarV(String b,String m,int y,double mi,String f,double val,int seats){ super(b,m,y,mi,f,val); this.seats=seats; } }
class BusV extends VehicleBase { int seatingCapacity; public BusV(String b,String m,int y,double mi,String f,double val,int cap){ super(b,m,y,mi,f,val); this.seatingCapacity=cap; } }
class TruckV extends VehicleBase { double loadCapacity; public TruckV(String b,String m,int y,double mi,String f,double val,double load){ super(b,m,y,mi,f,val); this.loadCapacity=load; } }

class p7 {
    public static double getFleetUtilization(VehicleBase[] vs){
        int active=0; for (VehicleBase v: vs) if (v!=null && !"Idle".equals(v.currentStatus)) active++;
        return vs.length==0?0:active*100.0/vs.length;
    }
    public static double calculateTotalMaintenanceCost(VehicleBase[] vs){ double c=0; for (VehicleBase v: vs) if (v!=null) c+=v.runningCost*0.1; return c; }
    public static VehicleBase[] getVehiclesByType(VehicleBase[] vs, Class<?> cls){
        ArrayList<VehicleBase> out=new ArrayList<>(); for (VehicleBase v: vs) if (v!=null && cls.isInstance(v)) out.add(v);
        return out.toArray(new VehicleBase[0]);
    }

    public static void main(String[] args){
        VehicleBase.companyName="TransitCo";
        VehicleBase[] fleet = {
            new CarV("Toyota","Corolla",2020,50000,"Petrol",800000,5),
            new BusV("Volvo","9400",2019,200000,"Diesel",5000000,50),
            new TruckV("Tata","Signa",2021,120000,"Diesel",3500000,16)
        };
        Driver d1=new Driver("D001","Alice","LMV");
        Driver d2=new Driver("D002","Bob","HMV");
        fleet[0].assignDriver(d1);
        fleet[1].assignDriver(d2);

        fleet[0].currentStatus="Trip"; fleet[0].updateMileage(120); fleet[0].calculateRunningCost(8,110,200);
        fleet[1].currentStatus="Trip"; fleet[1].updateMileage(340); fleet[1].calculateRunningCost(30,100,500);
        fleet[2].scheduleMaintenance();

        System.out.println("Total Vehicles: "+VehicleBase.totalVehicles+" | Fleet Value: "+VehicleBase.fleetValue+" | Fuel Used: "+VehicleBase.totalFuelConsumption);
        System.out.println("Utilization: "+getFleetUtilization(fleet)+"% | Maint Cost: "+calculateTotalMaintenanceCost(fleet));
        System.out.println("Buses: "+getVehiclesByType(fleet, BusV.class).length+" | Trucks: "+getVehiclesByType(fleet, TruckV.class).length);
    }
}
