# 📘 Book My Stay App
## Use Case 12: Data Persistence & System Recovery

---

## 📌 Overview
This module introduces **data persistence and recovery** in the Book My Stay App.  
It ensures that booking and inventory data are **saved to a file** and **restored after application restart**, preventing data loss.

---

## 🎯 Objective
To:
- Persist booking and inventory data
- Restore system state after restart
- Prevent data loss
- Simulate real-world durable systems

---

## 👤 Actors
- **System** – Initiates save/load operations
- **Persistence Service** – Handles file storage and retrieval

---

## 🔄 Flow
1. System prepares for shutdown
2. Booking and inventory state is serialized
3. Data is saved to a file
4. System restarts
5. Data is deserialized from file
6. System state is restored
7. Application resumes normally

---

## 🧠 Key Concepts Used

### 1. Stateful Applications
Application retains data across executions.

### 2. Persistence
Storing data in files to prevent loss.

### 3. Serialization
Converts objects into byte streams for storage.

### 4. Deserialization
Reconstructs objects from stored data.

### 5. Inventory Snapshot
Captures system state at a specific time.

### 6. Failure Tolerance
Handles missing/corrupt files safely.

---

## ⚙️ Functional Requirements
- Save booking and inventory data
- Load data during startup
- Maintain accurate restored state
- Handle file errors gracefully
- Continue system operation after recovery

---

## 📊 Data Structures Used
- **HashMap** → Inventory
- **ArrayList** → Bookings
- **File Handling** → Persistence
- **Serializable Interface** → Object storage

---

## ▶️ How to Compile and Run

```bash
javac UseCase12DataPersistenceRecovery.java
java UseCase12DataPersistenceRecovery