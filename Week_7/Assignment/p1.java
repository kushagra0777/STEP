public class p1 {
    void book(String roomType, int nights) {
        int cost = nights * 2000;
        System.out.println("Standard Booking: " + roomType + " for " + nights + " nights, Cost = " + cost);
    }
    void book(String roomType, int nights, double seasonalMultiplier) {
        double cost = nights * 2000 * seasonalMultiplier;
        System.out.println("Seasonal Booking: " + roomType + " for " + nights + " nights with multiplier " + seasonalMultiplier + ", Cost = " + cost);
    }
    void book(String roomType, int nights, double discount, int meals) {
        double base = nights * 2000;
        double cost = base - (base * discount / 100) + (meals * 500);
        System.out.println("Corporate Booking: " + roomType + " for " + nights + " nights with " + discount + "% discount + meals, Final Cost = " + cost);
    }
    void book(String roomType, int nights, int guests, int decorationFee, int cateringCost) {
        int base = nights * 5000;
        int cost = base + (guests * cateringCost) + decorationFee;
        System.out.println("Wedding Package: " + roomType + " for " + nights + " nights, Guests = " + guests + ", Decorations = " + decorationFee + ", Catering = " + (guests * cateringCost) + ", Total = " + cost);
    }
    public static void main(String[] args) {
        p1 h = new p1();
        h.book("Deluxe", 3);
        h.book("Suite", 4, 1.2);
        h.book("Executive", 5, 15.0, 3);
        h.book("Banquet Hall", 2, 200, 10000, 500);
    }
}
