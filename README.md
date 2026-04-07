# Book My Stay App – Use Case 4: Room Search & Availability Check

## 📌 Overview
This module introduces a **read-only search system** that allows guests to view available rooms without modifying system state.

It builds on Use Case 3 by clearly separating **read operations (search)** from **write operations (booking/inventory updates)**.

---

## 🎯 Use Case Goal
Enable guests to:
- View available room types
- See pricing and amenities
- Ensure no accidental modification of inventory

---

## 🧩 Key Concepts

### 1. Read-Only Access
Search operations only retrieve data:
```java
inventory.getAvailability(roomType);