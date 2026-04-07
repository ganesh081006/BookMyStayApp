/**
 *
 * CLASS RoomAllocationService
 *
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class is responsible for confirming
 * booking requests and assigning rooms.
 *
 * It ensures:
 * Each room ID is unique
 * Inventory is updated immediately
 * No room is double-booked
 *
 * @version 6.0
 */
public class RoomAllocationService {
    public class RoomAllocationService {
        /**
         * Stores all allocated room IDs to
         * prevent duplicate assignments.
         */
        private Set<String> allocatedRoomIds;
        /**
         * Stores assigned room IDs by room type.
         *
         * Key -> Room type
         * Value -> Set of assigned room IDs
         */
        private Map<String, Set<String>> assignedRoomsByType;
        /**
         * Initializes allocation tracking structures.
         */
        public RoomAllocationService() {...}
        /**
         * Confirms a booking request by assigning
         * a unique room ID and updating inventory.
         *
         * @param reservation booking request
         * @param inventory centralized room inventory
         */
        public void allocateRoom (Reservation reservation, RoomInventory inventory) {...}
        /**
         * Generates a unique room ID
         * for the given room type.
         *
         * @param roomType type of room
         * @return unique room ID
         */
        private String generateRoomId(String roomType) {...}
    }
}
/**
 *
 * MAIN CLASS UseCaseóRoomAllocation
 *
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class demonstrates how booking
 * requests are confirmed and rooms
 * are allocated safely.
 *
 * It consumes booking requests in FIFO
 * order and updates inventory immediately.
 *
 * @version 6.0
 */
public class UseCaseóRoomAllocation {
    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {...}
}
