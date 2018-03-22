package com.scarz.backend;

import com.scarz.backend.handlers.*;
import com.scarz.backend.transactions.*;

/**
 * Implements program logic, reads daily transaction files and handles each transaction to make changes to the
 * previous day's user account and available items files.
 */
public class Main {
    /**
     * Program entrypoint implementing program logic and components
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Create UserFile instance
        UserFile userFile = new UserFile("current_users.txt");

        // Open UserFile instance and read all users
        try {
            userFile.open();
        } catch (Exception ex) {
            System.out.println("Unable to read user file!");
            ex.printStackTrace();
            return;
        }

        // Create ItemFile instance
        ItemFile itemFile = new ItemFile("available_items.txt");

        // Open ItemFile instance and read all items
        try {
            itemFile.open();
        } catch (Exception ex) {
            System.out.println("Unable to read item file!");
            ex.printStackTrace();
            return;
        }

        // Create TransactionFile instance
        TransactionFile transactionFile = new TransactionFile("transactions.txt");

        // Add Transaction Serializers to TransactionFile instance
        transactionFile.addSerializer(TransactionType.LOGIN, new BasicTransaction.Serializer());
        transactionFile.addSerializer(TransactionType.LOGOUT, new BasicTransaction.Serializer());
        transactionFile.addSerializer(TransactionType.CREATE, new BasicTransaction.Serializer());
        transactionFile.addSerializer(TransactionType.DELETE, new BasicTransaction.Serializer());
        transactionFile.addSerializer(TransactionType.ADVERTISE, new AdvertiseTransaction.Serializer());
        transactionFile.addSerializer(TransactionType.BID, new BidTransaction.Serializer());
        transactionFile.addSerializer(TransactionType.REFUND, new RefundTransaction.Serializer());
        transactionFile.addSerializer(TransactionType.ADD_CREDIT, new BasicTransaction.Serializer());

        // Create handlers here
        IHandler[] handlers = new IHandler[] {
                new LoginHandler(userFile),
                new LogoutHandler(userFile),
                new CreateHandler(userFile),
                new DeleteHandler(userFile),

                new AdvertiseHandler(itemFile),
                new BidHandler(userFile, itemFile),
                new RefundHandler(userFile),
                new AddCreditHandler(userFile)
        };

        // Open TransactionFile instance and read all transactions
        try {
            transactionFile.open();
        } catch (Exception ex) {
            System.out.println("Unable to read transaction file!");
            ex.printStackTrace();
            return;
        }

        // Handle all transactions
        for (Transaction transaction : transactionFile.getTransactions()) {
            //      Handle each transaction in order, and depending on its Transaction Type, send it to be handled by the
            //      appropriate handler

            //      Once the handler makes the changes to the UserFile and ItemFile instances, then:

            //      Write output to the console mentioning if a transaction is not handled correctly, or is invalid
        }

        try {
            // Write the updated UserFile
            userFile.close();

            // Write the updated ItemFile
            itemFile.close();

            // Close transaction file
            transactionFile.close();
        } catch (Exception ex) {
            System.out.println("Unable to write to file!");
            ex.printStackTrace();
        }
    }
}
