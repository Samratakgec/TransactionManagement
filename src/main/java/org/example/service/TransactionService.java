package org.example.service;

import org.example.entity.UserAccount;
import org.example.entity.TransactionLog;
import org.example.utility.HiberUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class TransactionService {

    // Deposit money into a user account
    public void deposit(String accountNumber, Double amount) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HiberUtil.getSession();
            if (session == null) {
                System.err.println("Failed to open session");
                return;
            }

            tx = session.beginTransaction();

            // Fetch account
            UserAccount account = session.createQuery("from UserAccount where accountNumber = :accountNumber", UserAccount.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();

            if (account == null) {
                System.err.println("Account not found: " + accountNumber);
                return;
            }

            // Update balance
            account.setBalance(account.getBalance() + amount);
            session.saveOrUpdate(account);

            // Log transaction
            logTransaction(session, "DEPOSIT", amount, accountNumber, null);

            tx.commit();
            System.out.println("Deposited " + amount + " into account: " + accountNumber);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error during deposit: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    // Withdraw money from a user account
    public void withdraw(String accountNumber, Double amount) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HiberUtil.getSession();
            if (session == null) {
                System.err.println("Failed to open session");
                return;
            }

            tx = session.beginTransaction();

            // Fetch account
            UserAccount userAccount = session.createQuery("from UserAccount where accountNumber = :accountNumber", UserAccount.class)
                    .setParameter("accountNumber", accountNumber)
                    .uniqueResult();

            if (userAccount == null) {
                System.err.println("Account not found: " + accountNumber);
                return;
            }

            if (userAccount.getBalance() < amount) {
                System.err.println("Insufficient funds in account: " + accountNumber);
                return;
            }

            // Update the balance
            userAccount.setBalance(userAccount.getBalance() - amount);
            session.saveOrUpdate(userAccount);

            // Log transaction
            logTransaction(session, "WITHDRAW", amount, accountNumber, null);

            tx.commit();
            System.out.println("Withdrawn " + amount + " from account: " + accountNumber);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error during withdrawal: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    // Transfer money between accounts
    public void transfer(String sourceAccountNumber, String targetAccountNumber, Double amount) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HiberUtil.getSession();
            if (session == null) {
                System.err.println("Failed to open session");
                return;
            }

            tx = session.beginTransaction();

            // Fetch source and target accounts
            UserAccount sourceAccount = session.createQuery("from UserAccount where accountNumber = :accountNumber", UserAccount.class)
                    .setParameter("accountNumber", sourceAccountNumber)
                    .uniqueResult();

            UserAccount targetAccount = session.createQuery("from UserAccount where accountNumber = :accountNumber", UserAccount.class)
                    .setParameter("accountNumber", targetAccountNumber)
                    .uniqueResult();

            if (sourceAccount == null || targetAccount == null) {
                System.err.println("One or both accounts not found");
                return;
            }

            if (sourceAccount.getBalance() < amount) {
                System.err.println("Insufficient funds in source account: " + sourceAccountNumber);
                return;
            }

            // Update balances
            sourceAccount.setBalance(sourceAccount.getBalance() - amount);
            targetAccount.setBalance(targetAccount.getBalance() + amount);
            session.saveOrUpdate(sourceAccount);
            session.saveOrUpdate(targetAccount);

            // Log transaction
            logTransaction(session, "TRANSFER", amount, sourceAccountNumber, targetAccountNumber);

            tx.commit();
            System.out.println("Transferred " + amount + " from " + sourceAccountNumber + " to " + targetAccountNumber);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error during transfer: " + e.getMessage());
        } finally {
            if (session != null) session.close();
        }
    }

    // Helper method to log transactions
    private void logTransaction(Session session, String type, Double amount, String source, String target) {
        try {
            TransactionLog log = new TransactionLog();
            log.setTransactionType(type);
            log.setAmount(amount);
            log.setSourceAccount(source);
            log.setTargetAccount(target);
            log.setTimestamp(LocalDateTime.now());
            session.save(log);
        } catch (Exception e) {
            System.err.println("Error logging transaction: " + e.getMessage());
        }
    }
}
