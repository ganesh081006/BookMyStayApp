# Book My Stay App – Use Case 5: Booking Request Queue (FIFO)

## 📌 Overview
This module introduces a **Booking Request Queue** to handle multiple guest booking requests fairly using the **FIFO (First-Come-First-Served)** principle.

It ensures that booking requests are processed in the exact order they are received.

---

## 🎯 Use Case Goal
- Accept booking requests from guests
- Store them in order of arrival
- Ensure fairness in request handling
- Prepare for later allocation (no inventory updates yet)

---

## 🧩 Key Concepts

### 1. Problem of Simultaneous Requests
Multiple users may request bookings at the same time:
- Without ordering → unfair processing
- Leads to inconsistent booking outcomes

---

### 2. Queue Data Structure
```java
Queue<Reservation>