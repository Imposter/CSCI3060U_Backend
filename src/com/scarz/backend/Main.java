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

                new AdvertiseHandler(userFile, itemFile),
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
            // Get handler for type
            boolean handled = false;
            for (IHandler handler : handlers) {
                if (handler.getType() == transaction.getType()) {
                    // Handle
                    boolean success = handler.handle(transaction);
                    if (!success) {
                        System.out.printf("ERROR: [%s] Error handling transaction\r\n", handler.getName());
                    }

                    handled = true;
                    break;
                }
            }

            if (!handled) {
                System.out.printf("ERROR: Failed to handle transaction of type %d\r\n", transaction.getType());
            }
        }

        // Check if item listing has ended
        for (Item item : itemFile.getItems()) {
            int daysLeft = item.getDaysToAuction();
            if (daysLeft == 0) {
                // Give money to seller
                User seller = userFile.getUserByName(item.getSellerUserName());
                seller.setCredits(seller.getCredits() + item.getCurrentBid());

                // Remove item from list
                itemFile.removeItem(item);

                System.out.printf("INFO: User %s won bid on item %s by %s for %.2f\r\n", item.getBidderUserName(),
                        item.getName(), item.getSellerUserName(), item.getCurrentBid());

                continue;
            }

            // Decrement the amount of days left to auction if the item was not just posted
            if (!item.isNewItem())
                item.setDaysToAuction(daysLeft - 1);
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

        System.out.println("All done!");
    }
}
