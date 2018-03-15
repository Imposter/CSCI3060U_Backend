package com.scarz.backend;

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
        // TODO: Implement the following:

        // Create UserFile instance
        // Open UserFile instance and read all users

        // Create ItemFile instance
        // Open ItemFile instance and read all items

        // Create TransactionFile instance
        // Add Transaction Serializers to TransactionFile instance
        // Read all transactions
        //      Handle each transaction in order, and depending on its Transaction Type, send it to be handled by the
        //      appropriate handler
        //      Once the handler makes the changes to the UserFile and ItemFile instances, then:
        //      Write output to the console mentioning if a transaction is not handled correctly, or is invalid

        // Write the updated UserFile
        // Write the updated ItemFile

        // Close file handles
    }
}
