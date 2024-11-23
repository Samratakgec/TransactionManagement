# **Banking System Application**

## **Overview**
This project is a simple **Banking System Application** developed in Java using **Hibernate ORM**. It demonstrates basic banking functionalities such as account creation, deposit, withdrawal, and fund transfer while ensuring the implementation of **ACID properties** for database transactions.

> **Note**: Security and authentication mechanisms are not implemented in this project. It is intended to focus solely on core banking operations and transaction management.

---

## **Features**
The application supports the following functionalities:

1. **Account Management**:
   - Create a new user account with an initial balance.
   - View account details (balance and account information).

2. **Transaction Management**:
   - Deposit money into an account.
   - Withdraw money from an account.
   - Transfer funds between two accounts.

3. **Database Transactions**:
   - Ensures **ACID (Atomicity, Consistency, Isolation, Durability)** properties for all database operations.

4. **Logging**:
   - Logs all transactions (deposits, withdrawals, transfers) into a `TransactionLog` table.

---

## **Technologies Used**
- **Java**: Core programming language for the application.
- **Hibernate ORM**: For database interaction and transaction management.
- **SQL Database**: Lightweight,for testing purposes.
- **Maven**: Build and dependency management tool.

---

## **Setup and Installation**

### **Prerequisites**
- Java JDK (Version 8 or higher)
- Apache Maven
- IDE (IntelliJ IDEA, Eclipse, etc.) or a text editor
- H2 Database (included in dependencies)

---
## **Testing ACID Properties**
To test ACID properties:
1. **Atomicity**:
   - Simulate a failure during a transaction (e.g., exception in the middle of a deposit or withdrawal).
   - Verify that no partial changes are made to the database (e.g., balances remain unchanged).

2. **Consistency**:
   - Ensure that the database is in a valid state before and after any transaction.
   - For example, during a transfer, the sum of balances in the source and target accounts should remain constant.

3. **Isolation**:
   - Test multiple transactions running in parallel.
   - Ensure that intermediate states of one transaction are not visible to others (e.g., balances of accounts being updated should not appear inconsistent).

4. **Durability**:
   - After committing a transaction, simulate a server crash or application failure.
   - Verify that changes persist in the database when the application is restarted.

---

## **Limitations**
1. **No Authentication**:
   - The application does not implement user authentication or access control.
   - It assumes users know account numbers for all operations.

2. **Single-Threaded**:
   - The system is designed to handle one transaction at a time.
   - It does not support concurrent or multi-threaded transaction handling.

3. **Basic User Interface**:
   - The application runs via a command-line interface (CLI).
   - No GUI or web interface is provided.

---

## **Future Improvements**
1. **User Authentication**:
   - Add secure login and user access control.

2. **RESTful APIs**:
   - Develop APIs to enable remote interaction and integration with external systems.

3. **GUI or Web Interface**:
   - Build a user-friendly interface for account and transaction management.

4. **Concurrent Transaction Handling**:
   - Enhance the system to process multiple transactions simultaneously.

5. **Enhanced Logging**:
   - Add more detailed logs to track transaction histories and error reporting.

---



