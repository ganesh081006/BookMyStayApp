# 📘 Book My Stay App
## Use Case 11: Concurrent Booking Simulation (Thread Safety)

---

## 📌 Overview
This module demonstrates how **concurrent booking requests** can affect system consistency and how **synchronization ensures thread safety**.

It simulates multiple users booking rooms simultaneously using **multi-threading in Java**, ensuring correct inventory updates without conflicts.

---

## 🎯 Objective
To:
- Simulate concurrent booking requests
- Prevent race conditions
- Ensure thread-safe inventory updates
- Maintain consistent system state

---

## 👤 Actors
- **Multiple Guests** – Submit booking requests concurrently
- **Booking Processor Threads** – Process requests in parallel

---

## 🔄 Flow
1. Multiple booking requests are added to a shared queue
2. Multiple threads process requests simultaneously
3. Threads access shared resources using synchronization
4. Inventory is updated inside critical sections
5. System ensures no double allocation occurs

---

## 🧠 Key Concepts Used

### 1. Race Conditions
Occurs when multiple threads modify shared data simultaneously, leading to unpredictable results.

### 2. Thread Safety
Ensures correct behavior when multiple threads access shared resources.

### 3. Shared Mutable State
- Booking Queue
- Inventory Map

Both are shared across threads and must be protected.

### 4. Critical Sections
Code blocks that must not be executed by multiple threads at the same time.

### 5. Synchronization
Using `synchronized` ensures:
- Only one thread accesses critical section at a time
- Prevents data corruption

### 6. Concurrency vs Parallelism
Focus is on **correctness under concurrent execution**, not performance.

---

## ⚙️ Functional Requirements
- Simulate multiple booking requests
- Use shared queue and inventory
- Ensure thread-safe updates
- Prevent double booking
- Maintain consistent system state

---

## 📊 Data Structures Used
- **Queue (LinkedList)** → Booking requests
- **HashMap** → Inventory tracking
- **Threads** → Concurrent execution

---

## ▶️ How to Compile and Run

```bash
javac UseCase11ConcurrentBookingSimulation.java
java UseCase11ConcurrentBookingSimulation