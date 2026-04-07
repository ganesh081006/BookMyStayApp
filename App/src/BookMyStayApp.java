import java.util.*;

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    // Booking Request
    static class BookingRequest {
        String bookingId;
        String roomType;

        BookingRequest(String bookingId, String roomType) {
            this.bookingId = bookingId;
            this.roomType = roomType;
        }
    }

    // Shared Booking System
    static class BookingSystem {

        // Shared Queue
        Queue<BookingRequest> bookingQueue = new LinkedList<>();

        // Inventory (roomType -> count)
        Map<String, Integer> inventory = new HashMap<>();

        // Lock object for synchronization
        private final Object lock = new Object();

        // Add booking request (Producer)
        public void addRequest(BookingRequest request) {
            synchronized (lock) {
                bookingQueue.add(request);
                System.out.println(Thread.currentThread().getName() +
                        " added request: " + request.bookingId);
            }
        }

        // Process booking (Consumer)
        public void processRequest() {
            synchronized (lock) {

                if (bookingQueue.isEmpty()) {
                    return;
                }

                BookingRequest request = bookingQueue.poll();

                if (request == null) return;

                String roomType = request.roomType;

                // Critical Section: Check + Update
                if (inventory.getOrDefault(roomType, 0) > 0) {
                    inventory.put(roomType, inventory.get(roomType) - 1);

                    System.out.println(Thread.currentThread().getName() +
                            " SUCCESS: " + request.bookingId +
                            " -> " + roomType);
                } else {
                    System.out.println(Thread.currentThread().getName() +
                            " FAILED: No rooms for " + roomType);
                }
            }
        }

        // Initialize inventory
        public void addRoom(String roomType, int count) {
            inventory.put(roomType, count);
        }

        public void displayInventory() {
            System.out.println("\nFinal Inventory: " + inventory);
        }
    }

    // Worker Thread
    static class BookingProcessor extends Thread {

        BookingSystem system;

        BookingProcessor(BookingSystem system, String name) {
            super(name);
            this.system = system;
        }

        public void run() {
            for (int i = 0; i < 3; i++) {
                system.processRequest();
                try {
                    Thread.sleep(100); // simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Main Method
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Initialize inventory
        system.addRoom("Deluxe", 2);
        system.addRoom("Standard", 1);

        // Add requests (simulating multiple users)
        system.addRequest(new BookingRequest("B201", "Deluxe"));
        system.addRequest(new BookingRequest("B202", "Deluxe"));
        system.addRequest(new BookingRequest("B203", "Deluxe"));
        system.addRequest(new BookingRequest("B204", "Standard"));
        system.addRequest(new BookingRequest("B205", "Standard"));

        // Create multiple threads
        BookingProcessor t1 = new BookingProcessor(system, "Thread-1");
        BookingProcessor t2 = new BookingProcessor(system, "Thread-2");
        BookingProcessor t3 = new BookingProcessor(system, "Thread-3");

        // Start threads
        t1.start();
        t2.start();
        t3.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display final state
        system.displayInventory();
    }
}