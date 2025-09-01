import java.util.*;

class Room {
    final String roomNumber;
    final String roomType;
    final double pricePerNight;
    boolean isAvailable;
    final int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.maxOccupancy = maxOccupancy;
        this.isAvailable = true;
    }
}

class Guest {
    private static int seq=0;
    final String guestId;
    final String guestName;
    final String phoneNumber;
    final String email;
    final String[] bookingHistory;
    int historyCount=0;

    public Guest(String guestName, String phoneNumber, String email) {
        this.guestId = String.format("G%03d", ++seq);
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new String[50];
    }

    public void addHistory(String bookingId) {
        if (historyCount<bookingHistory.length) bookingHistory[historyCount++]=bookingId;
    }
}

class p3 {
    private static int seq=0;
    private static int totalBookings=0;
    private static double hotelRevenue=0;
    private static String hotelName="Unset";
    final String bookingId;
    final Guest guest;
    final Room room;
    final String checkInDate;
    final String checkOutDate;
    double totalAmount;
    boolean active;

    public p3(Guest guest, Room room, String checkInDate, String checkOutDate, int nights) {
        this.bookingId = String.format("B%03d", ++seq);
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = nights * room.pricePerNight;
        this.active = true;
        totalBookings++;
        hotelRevenue += totalAmount;
        guest.addHistory(bookingId);
        room.isAvailable = false;
    }

    public static void setHotelName(String name){ hotelName=name; }
    public static int getTotalBookings(){ return totalBookings; }
    public static double getTotalRevenue(){ return hotelRevenue; }

    public static double getOccupancyRate(Room[] rooms) {
        int occ=0, tot=0;
        for (Room r: rooms) if (r!=null){ tot++; if(!r.isAvailable) occ++; }
        return tot==0?0:(occ*100.0/tot);
    }

    public static String getMostPopularRoomType(p3[] bookings) {
        Map<String,Integer> map = new HashMap<>();
        for (p3 b: bookings) if (b!=null) map.put(b.room.roomType, map.getOrDefault(b.room.roomType,0)+1);
        String ans="N/A"; int best=0;
        for (String k: map.keySet()) if (map.get(k)>best){ best=map.get(k); ans=k; }
        return ans;
    }

    public static p3 makeReservation(Guest g, Room r, String in, String out, int nights) {
        if (r.isAvailable) return new p3(g,r,in,out,nights);
        return null;
    }

    public static void cancelReservation(p3 b) {
        if (b!=null && b.active){ b.active=false; b.room.isAvailable=true; }
    }

    public static boolean checkAvailability(Room r){ return r.isAvailable; }

    public static void main(String[] args) {
        setHotelName("Aurora Hotel");
        Room[] rooms = {
            new Room("101","Deluxe",4500,2),
            new Room("102","Deluxe",4500,2),
            new Room("201","Suite",8000,4),
            new Room("301","Standard",3000,2)
        };

        Guest g1 = new Guest("Alice","9991112222","a@x.com");
        Guest g2 = new Guest("Bob","8881112222","b@x.com");

        p3[] bookings = new p3[10];
        bookings[0]=makeReservation(g1, rooms[0], "2025-09-10","2025-09-12",2);
        bookings[1]=makeReservation(g2, rooms[2], "2025-09-15","2025-09-18",3);

        System.out.println("Total Bookings: "+getTotalBookings());
        System.out.println("Total Revenue: "+getTotalRevenue());
        System.out.println("Occupancy Rate: "+getOccupancyRate(rooms)+"%");
        System.out.println("Popular Type: "+getMostPopularRoomType(bookings));

        cancelReservation(bookings[0]);
        System.out.println("Room 101 available: "+rooms[0].isAvailable);
    }
}
