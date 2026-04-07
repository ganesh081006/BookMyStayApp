# Book My Stay App – Use Case 3 (Version 3.1)

## 📌 Overview
This module demonstrates **Centralized Room Inventory Management** using Core Java and HashMap.

The goal is to replace scattered availability variables with a **single source of truth**, improving consistency, scalability, and maintainability.

---

## 🎯 Use Case Goal
Introduce centralized inventory management by using a `HashMap<String, Integer>` to manage room availability.

---

## 🧩 Key Concepts

### 1. Problem of Scattered State
Previous implementations used separate variables:
- Hard to maintain
- Error-prone
- Not scalable

### 2. HashMap Usage
```java
HashMap<String, Integer>