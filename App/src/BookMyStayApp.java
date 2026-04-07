import java.util.*;

// Main Class
public class UseCase10BookingCancellation {

    // Booking class
    static class Booking {
        String bookingId;
        String roomType;
        String roomId;
        boolean isActive;

        Booking(String bookingId, String roomType, String roomId) {
            this.bookingId = bookingId;
            this.roomType = roomType;
            this.roomId = roomId;
            this.isActive = true;
        }
    }

    // Cancellation Service
    static class CancellationService {

        // Store bookings
        Map<String, Booking> bookingMap = new HashMap<>();

        // Inventory (roomType -> count)
        Map<String, Integer> inventory = new HashMap<>();

        // Available room IDs (roomType -> Queue)
        Map<String, Queue<String>> availableRooms = new HashMap<>();

        // Rollback Stack (LIFO)
        Stack<String> rollbackStack = new Stack<>();

        // Initialize inventory
        public void addRoom(String roomType, String roomId) {
            availableRooms.putIfAbsent(roomType, new LinkedList<>());
            inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);

            availableRooms.get(roomType).add(roomId);
        }

        // Booking method (for testing)
        public void bookRoom(String bookingId, String roomType) {
            if (!availableRooms.containsKey(roomType) || availableRooms.get(roomType).isEmpty()) {
                System.out.println("No rooms available for type: " + roomType);
                return;
            }

            String roomId = availableRooms.get(roomType).poll();
            inventory.put(roomType, inventory.get(roomType) - 1);

            Booking booking = new Booking(bookingId, roomType, roomId);
            bookingMap.put(bookingId, booking);

            System.out.println("Booking Confirmed: " + bookingId + " -> Room " + roomId);
        }

        // Cancellation logic
        public void cancelBooking(String bookingId) {

            System.out.println("\nProcessing cancellation for: " + bookingId);

            // Step 1: Validate booking
            if (!bookingMap.containsKey(bookingId)) {
                System.out.println("Cancellation Failed: Booking does not exist.");
                return;
            }

            Booking booking = bookingMap.get(bookingId);

            if (!booking.isActive) {
                System.out.println("Cancellation Failed: Booking already cancelled.");
                return;
            }

            // Step 2: Push to rollback stack
            rollbackStack.push(booking.roomId);

            // Step 3: Restore inventory
            inventory.put(booking.roomType, inventory.get(booking.roomType) + 1);

            // Step 4: Add room back to available pool
            availableRooms.get(booking.roomType).add(booking.roomId);

            // Step 5: Update booking status
            booking.isActive = false;

            // Step 6: Confirm state restoration
            System.out.println("Cancellation Successful for Booking ID: " + bookingId);
            System.out.println("Room " + booking.roomId + " released back to inventory.");
        }

        // Display system state
        public void displayStatus() {
            System.out.println("\n--- Current Inventory ---");
            for (String type : inventory.keySet()) {
                System.out.println(type + " : " + inventory.get(type));
            }

            System.out.println("\n--- Rollback Stack ---");
            System.out.println(rollbackStack);
        }
    }

    // Main method
    public static void main(String[] args) {

        CancellationService service = new CancellationService();

        // Initialize rooms
        service.addRoom("Deluxe", "D1");
        service.addRoom("Deluxe", "D2");
        service.addRoom("Standard", "S1");

        // Book rooms
        service.bookRoom("B101", "Deluxe");
        service.bookRoom("B102", "Standard");

        service.displayStatus();

        // Cancel booking
        service.cancelBooking("B101");

        // Try invalid cancellation
        service.cancelBooking("B999");

        // Try duplicate cancellation
        service.cancelBooking("B101");

        service.displayStatus();
    }
}