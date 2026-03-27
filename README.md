# 🏨 Book My Stay App – Use Case 2 (Version 2.0)

## 📌 Overview
This module demonstrates **object-oriented design principles** using a simple Hotel Booking Management System.

The focus of this use case is:
- Domain modeling using **abstraction and inheritance**
- Understanding **polymorphism and encapsulation**
- Representing **static room availability without data structures**

---

## 🎯 Use Case 2: Basic Room Types & Static Availability

### Goal
Introduce object-oriented design concepts before moving into complex data structures.

---

## 👤 Actor
User runs the application to view room types and their availability.

---

## 🔄 Application Flow
1. Application starts
2. Room objects are created
3. Availability is stored using simple variables
4. Room details are displayed
5. Application exits

---

## 🧠 Concepts Used

### 🔹 Abstract Class
- `Room` is an abstract class
- Defines common structure for all room types

### 🔹 Inheritance
- `SingleRoom`, `DoubleRoom`, `SuiteRoom` extend `Room`

### 🔹 Polymorphism
- All room objects are handled using `Room` reference type

### 🔹 Encapsulation
- Fields are private with getter methods

### 🔹 Static Availability
- Availability stored using simple variables
- Demonstrates limitations of non-scalable approach

### 🔹 Separation of Concerns
- Room → Represents structure
- Availability → Represents state

---

## 📂 Project Structure
