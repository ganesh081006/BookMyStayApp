import java.io.*;
import java.util.*;

// Main Class
public class UseCase12DataPersistenceRecovery {

    // Booking Class (Serializable)
    static class Booking implements Serializable {
        String bookingId;
        String roomType;

        Booking(String bookingId, String roomType) {
            this.bookingId = bookingId;
            this.roomType = roomType;
        }

        public String toString() {
            return bookingId + " (" + roomType + ")";
        }
    }

    // System State Class
    static class SystemState implements Serializable {
        Map<String, Integer> inventory;
        List<Booking> bookings;

        SystemState(Map<String, Integer> inventory, List<Booking> bookings) {
            this.inventory = inventory;
            this.bookings = bookings;
        }
    }

    // Persistence Service
    static class PersistenceService {

        private static final String FILE_NAME = "system_state.ser";

        // Save state
        public static void save(SystemState state) {
            try (ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

                oos.writeObject(state);
                System.out.println("System state saved successfully.");

            } catch (IOException e) {
                System.out.println("Error saving system state: " + e.getMessage());
            }
        }

        // Load state
        public static SystemState load() {
            try (ObjectInputStream ois =
                         new ObjectInputStream(new FileInputStream(FILE_NAME))) {

                System.out.println("System state loaded successfully.");
                return (SystemState) ois.readObject();

            } catch (FileNotFoundException e) {
                System.out.println("No previous state found. Starting fresh.");
            } catch (Exception e) {
                System.out.println("Error loading state. Starting with clean state.");
            }

            // fallback
            return new SystemState(new HashMap<>(), new ArrayList<>());
        }
    }

    // Booking System
    static class BookingSystem {

        Map<String, Integer> inventory = new HashMap<>();
        List<Booking> bookings = new ArrayList<>();

        // Add inventory
        public void addRoom(String type, int count) {
            inventory.put(type, count);
        }

        // Book room
        public void bookRoom(String bookingId, String roomType) {
            if (inventory.getOrDefault(roomType, 0) > 0) {
                inventory.put(roomType, inventory.get(roomType) - 1);
                bookings.add(new Booking(bookingId, roomType));
                System.out.println("Booked: " + bookingId);
            } else {
                System.out.println("No rooms available for " + roomType);
            }
        }

        // Display state
        public void display() {
            System.out.println("\nInventory: " + inventory);
            System.out.println("Bookings: " + bookings);
        }

        // Save state
        public void saveState() {
            PersistenceService.save(new SystemState(inventory, bookings));
        }

        // Load state
        public void loadState() {
            SystemState state = PersistenceService.load();
            this.inventory = state.inventory;
            this.bookings = state.bookings;
        }
    }

    // Main Method
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Step 1: Load previous state
        system.loadState();

        // Step 2: Show recovered state
        System.out.println("\n--- System State After Recovery ---");
        system.display();

        // Step 3: Perform operations
        system.addRoom("Deluxe", 2);   // only applies if fresh
        system.addRoom("Standard", 1);

        system.bookRoom("B301", "Deluxe");
        system.bookRoom("B302", "Standard");

        // Step 4: Show updated state
        System.out.println("\n--- Updated System State ---");
        system.display();

        // Step 5: Save state before shutdown
        system.saveState();
    }
}