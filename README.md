# 📘 Book My Stay App
## Use Case 10: Booking Cancellation & Inventory Rollback

---

## 📌 Overview
This module implements the **Booking Cancellation feature** in the *Book My Stay App*. It ensures that when a booking is cancelled, all system state changes are safely reversed, maintaining **inventory consistency** and preventing issues like double-booking.

The implementation focuses on **core Java concepts and data structures**, emphasizing system behavior rather than UI.

---

## 🎯 Objective
To design a **safe and controlled cancellation system** that:
- Validates booking requests
- Reverses previous operations
- Restores room availability
- Maintains a consistent system state

---

## 👤 Actors
- **Guest** – Initiates cancellation request
- **Cancellation Service** – Validates and performs rollback

---

## 🔄 Cancellation Flow
1. Guest requests booking cancellation
2. System validates booking existence
3. Checks if booking is already cancelled
4. Pushes room ID to rollback stack
5. Restores inventory count
6. Adds room back to available pool
7. Updates booking status
8. Confirms successful rollback

---

## 🧠 Key Concepts Used

### 1. State Reversal
Cancelling a booking requires undoing previous operations without affecting system stability.

### 2. Stack Data Structure (LIFO)
A **Stack** is used to track released room IDs.
- Last-In-First-Out (LIFO)
- Mimics real-world undo operations

### 3. Controlled Mutation
Operations are executed in a strict order to avoid partial updates and inconsistencies.

### 4. Inventory Restoration
Room availability is updated immediately after cancellation to reflect real-time system state.

### 5. Validation Logic
Ensures:
- Booking exists
- Booking is active
- Duplicate cancellations are prevented

---

## ⚙️ Functional Requirements
- Allow cancellation of confirmed bookings only
- Validate booking before cancellation
- Restore inventory accurately
- Release room IDs back to pool
- Prevent invalid or duplicate cancellations

---

## 📊 Data Structures Used
- **HashMap** → Store bookings and inventory
- **Queue (LinkedList)** → Manage available rooms
- **Stack** → Handle rollback operations

---

## ▶️ How to Compile and Run

```bash
javac UseCase10BookingCancellation.java
java UseCase10BookingCancellation