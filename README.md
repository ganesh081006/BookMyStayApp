# Book My Stay App – Use Case 6: Reservation Confirmation & Room Allocation

## 📌 Overview
This module introduces the **final step in the booking pipeline** — confirming reservations and allocating rooms safely.

It integrates:
- Booking Request Queue (Use Case 5)
- Centralized Inventory (Use Case 3)
- Controlled Allocation Logic

The system ensures **no double-booking** and maintains **consistent inventory state**.

---

## 🎯 Use Case Goal

- Process booking requests in FIFO order
- Assign a unique room ID to each reservation
- Prevent duplicate room allocation
- Update inventory immediately after booking
- Ensure complete system consistency

---

## 🧩 Key Concepts

### 1. Problem of Double Booking
Without safeguards:
- Same room may be assigned multiple times
- Leads to conflicts and invalid reservations

---

### 2. Set for Uniqueness
```java
Set<String>