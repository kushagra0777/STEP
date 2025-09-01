class p4 {
    private static int vehicleCounter = 0;
    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName;
    private static int rentalDays = 0;

    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int totalRentalDays;

    public p4(String brand, String model, double rentPerDay) {
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.vehicleId = generateVehicleId();
        this.isAvailable = true;
        totalVehicles++;
        this.totalRentalDays = 0;
    }

    private static String generateVehicleId() {
        vehicleCounter++;
        return String.format("V%03d", vehicleCounter);
    }

    public double rentVehicle(int days) {
        if (isAvailable) {
            isAvailable = false;
            double amount = calculateRent(days);
            totalRevenue += amount;
            rentalDays += days;
            totalRentalDays += days;
            return amount;
        }
        return 0;
    }

    public void returnVehicle() {
        isAvailable = true;
    }

    public double calculateRent(int days) {
        return rentPerDay * days;
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle ID: " + vehicleId + ", Brand: " + brand + ", Model: " + model + ", Rent/Day: " + rentPerDay + ", Available: " + isAvailable + ", Total Rental Days: " + totalRentalDays);
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        return rentalDays == 0 ? 0 : totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company: " + companyName + ", Total Vehicles: " + totalVehicles + ", Total Revenue: " + totalRevenue + ", Avg Rent/Day: " + getAverageRentPerDay());
    }

    public static void main(String[] args) {
        p4.setCompanyName("City Rentals");

        p4 v1 = new p4("Toyota", "Camry", 100);
        p4 v2 = new p4("Honda", "Civic", 80);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();

        double bill1 = v1.rentVehicle(3);
        System.out.println("Bill for v1: " + bill1);

        v1.returnVehicle();

        double bill2 = v2.rentVehicle(5);
        System.out.println("Bill for v2: " + bill2);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();

        p4.displayCompanyStats();
    }
}
