package org.example.service;

import org.example.entity.UserAccount;
import org.example.utility.HiberUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserAccountService {

    // Create a new user account
    public void createAccount(String accountNumber, Double initialBalance) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HiberUtil.getSession();
            if (session == null) {
                System.err.println("Failed to open session");
                return;
            }

            tx = session.beginTransaction();

            // Create a new UserAccount
            UserAccount userAccount = new UserAccount();
            userAccount.setAccountNumber(accountNumber);
            userAccount.setBalance(initialBalance);
            session.save(userAccount);

            tx.commit();
            System.out.println("Account created: " + accountNumber + " with balance: " + initialBalance);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error creating account: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    // Read account details
    public void readAccountDetails(String accountNumber) {
        Session session = null;
        try {
            session = HiberUtil.getSession();
            if (session == null) {
                System.err.println("Failed to open session");
                return;
            }

            // Fetch the user account
            UserAccount userAccount = session.createQuery("from UserAccount where accountNumber = :accountNumber", UserAccount.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();

            if (userAccount == null) {
                System.err.println("Account not found: " + accountNumber);
                return;
            }

            // Print account details
            System.out.println("Account Details: ");
            System.out.println("Account Number: " + userAccount.getAccountNumber());
            System.out.println("Balance: " + userAccount.getBalance());
        } catch (Exception e) {
            System.err.println("Error reading account details: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }
}
