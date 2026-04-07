# Book My Stay App – Use Case 7: Add-On Service Selection

## 📌 Overview
This module extends the booking system to support **optional add-on services** such as:
- Breakfast
- Spa Access
- Airport Pickup

It demonstrates how to add real-world business features **without modifying core booking or inventory logic**.

---

## 🎯 Use Case Goal

- Allow guests to select additional services
- Attach multiple services to a reservation
- Calculate total additional cost
- Keep booking and inventory logic unchanged

---

## 🧩 Key Concepts

### 1. Business Extensibility
- New features are added without modifying core booking system
- Follows open/closed principle

---

### 2. One-to-Many Relationship

```java
Map<String, List<AddOnService>>