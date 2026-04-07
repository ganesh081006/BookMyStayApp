import java.util.HashMap;
import java.util.Map;

public class RoomInventory {

    // Centralized storage for room availability
    private HashMap<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register a room type with initial count
    public void addRoomType(String roomType, int count) {
        if (count < 0) {
            System.out.println("Invalid room count for " + roomType);
            return;
        }
        inventory.put(roomType, count);
    }

    // Get availability of a specific room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update room availability (controlled update)
    public void updateAvailability(String roomType, int newCount) {
        if (!inventory.containsKey(roomType)) {
            System.out.println("Room type does not exist: " + roomType);
            return;
        }
        if (newCount < 0) {
            System.out.println("Invalid update value.");
            return;
        }
        inventory.put(roomType, newCount);
    }

    // Book a room (reduce count safely)
    public boolean bookRoom(String roomType) {
        int available = getAvailability(roomType);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        } else {
            return false;
        }
    }

    // Cancel a booking (increase count safely)
    public void cancelBooking(String roomType) {
        int available = getAvailability(roomType);
        inventory.put(roomType, available + 1);
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}