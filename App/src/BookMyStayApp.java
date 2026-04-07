import java.util.*;

// Represents a booking (simplified from Use Case 6)
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

// Represents an Add-On Service
class AddOnService {
    private String serviceName;
    private double price;

    public AddOnService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println(serviceName + " : ₹" + price);
    }
}

// Manages mapping between Reservation and Services
class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Added service '" + service.getServiceName() +
                "' to Reservation " + reservationId);
    }

    // Get services for reservation
    public List<AddOnService> getServices(String reservationId) {
        return serviceMap.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total cost
    public double calculateTotalServiceCost(String reservationId) {
        double total = 0.0;

        List<AddOnService> services = getServices(reservationId);
        for (AddOnService s : services) {
            total += s.getPrice();
        }

        return total;
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {
        List<AddOnService> services = getServices(reservationId);

        System.out.println("\nServices for Reservation " + reservationId + ":");

        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        for (AddOnService s : services) {
            s.display();
        }

        System.out.println("Total Add-On Cost: ₹" + calculateTotalServiceCost(reservationId));
    }
}

// Driver Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        // Step 1: Create Reservation (Already confirmed in UC6)
        Reservation reservation = new Reservation("R101", "Alice", "Suite");
        reservation.display();

        // Step 2: Initialize Service Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Step 3: Guest selects services
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService spa = new AddOnService("Spa Access", 1500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 800);

        // Step 4: Add services to reservation
        manager.addService(reservation.getReservationId(), breakfast);
        manager.addService(reservation.getReservationId(), spa);
        manager.addService(reservation.getReservationId(), airportPickup);

        // Step 5: Display selected services
        manager.displayServices(reservation.getReservationId());
    }
}