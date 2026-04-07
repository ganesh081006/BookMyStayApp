import java.util.*;

// Reservation Model (from previous use cases)
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double roomPrice;

    public Reservation(String reservationId, String guestName, String roomType, double roomPrice) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void display() {
        System.out.println("ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType +
                " | Price: ₹" + roomPrice);
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
        System.out.println("Reservation stored: " + reservation.getReservationId());
    }

    // Retrieve all reservations
    public List<Reservation> getAllReservations() {
        return history;
    }

    // Display history
    public void displayHistory() {
        System.out.println("\nBooking History:");

        if (history.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : history) {
            r.display();
        }
    }
}

// Reporting Service
class BookingReportService {

    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    // Generate summary report
    public void generateSummaryReport() {

        List<Reservation> reservations = history.getAllReservations();

        System.out.println("\nBooking Summary Report:");

        if (reservations.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        int totalBookings = reservations.size();
        double totalRevenue = 0;

        Map<String, Integer> roomTypeCount = new HashMap<>();

        for (Reservation r : reservations) {
            totalRevenue += r.getRoomPrice();

            String type = r.getRoomType();
            roomTypeCount.put(type, roomTypeCount.getOrDefault(type, 0) + 1);
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: ₹" + totalRevenue);

        System.out.println("\nBookings by Room Type:");
        for (String type : roomTypeCount.keySet()) {
            System.out.println(type + " : " + roomTypeCount.get(type));
        }
    }
}

// Driver Class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        // Step 1: Initialize Booking History
        BookingHistory history = new BookingHistory();

        // Step 2: Simulate confirmed bookings (from UC6)
        Reservation r1 = new Reservation("R101", "Alice", "Single", 1000);
        Reservation r2 = new Reservation("R102", "Bob", "Double", 2000);
        Reservation r3 = new Reservation("R103", "Charlie", "Suite", 5000);

        // Step 3: Store bookings
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Step 4: Admin views booking history
        history.displayHistory();

        // Step 5: Generate report
        BookingReportService reportService = new BookingReportService(history);
        reportService.generateSummaryReport();
    }
}