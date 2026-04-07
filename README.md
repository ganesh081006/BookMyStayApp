# Book My Stay App – Use Case 9: Error Handling & Validation

## 📌 Overview
This module strengthens system reliability by introducing:
- Input validation
- Custom exceptions
- Fail-fast error handling

It ensures that invalid inputs are caught early and do not corrupt system state.

---

## 🎯 Use Case Goal

- Validate booking inputs before processing
- Prevent invalid inventory updates
- Handle errors gracefully
- Maintain system stability

---

## 🧩 Key Concepts

### 1. Input Validation
- Ensures correct data before processing
- Prevents invalid state

---

### 2. Custom Exceptions

```java
class InvalidBookingException extends Exception