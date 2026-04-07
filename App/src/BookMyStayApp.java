import java.util.*;

// Custom Exception for Booking Errors
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory Service with Validation
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 0); // unavailable
    }

    public boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Safe update
    public void reduceRoom(String roomType) throws InvalidBookingException {
        int available = getAvailability(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }

        inventory.put(roomType, available - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + " : " + inventory.get(type));
        }
    }
}

// Reservation Model
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Validator Class (Fail-Fast)
class BookingValidator {

    public static void validate(Reservation reservation, RoomInventory inventory)
            throws InvalidBookingException {

        if (reservation.getGuestName() == null || reservation.getGuestName().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isValidRoomType(reservation.getRoomType())) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (inventory.getAvailability(reservation.getRoomType()) <= 0) {
            throw new InvalidBookingException("Selected room type is not available.");
        }
    }
}

// Booking Service with Error Handling
class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBooking(Reservation reservation) {

        try {
            // Step 1: Validate (Fail Fast)
            BookingValidator.validate(reservation, inventory);

            // Step 2: Allocate room
            inventory.reduceRoom(reservation.getRoomType());

            // Step 3: Confirm booking
            System.out.println("Booking successful for " +
                    reservation.getGuestName() +
                    " (" + reservation.getRoomType() + ")");

        } catch (InvalidBookingException e) {
            // Graceful failure handling
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}

// Driver Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        // Step 1: Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Step 2: Initialize Booking Service
        BookingService service = new BookingService(inventory);

        // Step 3: Test Cases

        // Valid booking
        service.processBooking(new Reservation("Alice", "Single"));

        // Invalid room type
        service.processBooking(new Reservation("Bob", "Deluxe"));

        // No availability
        service.processBooking(new Reservation("Charlie", "Suite"));

        // Empty guest name
        service.processBooking(new Reservation("", "Double"));

        // Valid booking again
        service.processBooking(new Reservation("David", "Double"));

        // Step 4: Final Inventory
        inventory.displayInventory();
    }
}