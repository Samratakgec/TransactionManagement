package org.example;

import org.example.service.TransactionService;
import org.example.service.UserAccountService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccountService userAccountService = new UserAccountService();
        TransactionService transactionService = new TransactionService();

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    // Create Account
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter initial balance: ");
                    Double initialBalance = scanner.nextDouble();
                    userAccountService.createAccount(accountNumber, initialBalance);
                }
                case 2 -> {
                    // Deposit Money
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter amount to deposit: ");
                    Double amount = scanner.nextDouble();
                    transactionService.deposit(accountNumber, amount);
                }
                case 3 -> {
                    // Withdraw Money
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter amount to withdraw: ");
                    Double amount = scanner.nextDouble();
                    transactionService.withdraw(accountNumber, amount);
                }
                case 4 -> {
                    // Transfer Money
                    System.out.print("Enter source account number: ");
                    String sourceAccountNumber = scanner.next();
                    System.out.print("Enter target account number: ");
                    String targetAccountNumber = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    Double amount = scanner.nextDouble();
                    transactionService.transfer(sourceAccountNumber, targetAccountNumber, amount);
                }
                case 5 -> {
                    // View Account Details
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    userAccountService.readAccountDetails(accountNumber);
                }
                case 6 -> {
                    // Exit
                    System.out.println("Exiting... Thank you for using the Banking System!");
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
