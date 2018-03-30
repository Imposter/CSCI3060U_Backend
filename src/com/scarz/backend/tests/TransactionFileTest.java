package com.scarz.backend.tests;

import com.scarz.backend.TransactionFile;
import com.scarz.backend.UserType;
import com.scarz.backend.transactions.AdvertiseTransaction;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

import java.util.List;

public class TransactionFileTest extends TestCase {
    public static final String TRANSACTIONS_FILE = "test_transactions.txt";

    public void testAddSerializer() {
        // Create transaction file and add a serializer
        TransactionFile file = new TransactionFile(TRANSACTIONS_FILE);
        file.addSerializer(TransactionType.LOGIN, new BasicTransaction.Serializer());
    }

    public void testOpen() throws Exception {
        // Write test data to file
        FileTestHelper.writeLinesToFile(TRANSACTIONS_FILE, new String[] {
                "10 selluser        SS 009999.99",
                "03 Rolex Watch               selluser        050 100.00",
                "00 selluser        SS 009999.99"
        });

        // Create transaction file and add a serializer
        TransactionFile file = new TransactionFile(TRANSACTIONS_FILE);
        file.addSerializer(TransactionType.LOGIN, new BasicTransaction.Serializer());
        file.addSerializer(TransactionType.ADVERTISE, new AdvertiseTransaction.Serializer());
        file.addSerializer(TransactionType.LOGOUT, new BasicTransaction.Serializer());

        // Open file
        file.open();

        // Close file
        file.close();
    }

    public void testGetTransactions() throws Exception {
        // Write test data to file
        FileTestHelper.writeLinesToFile(TRANSACTIONS_FILE, new String[] {
                "10 selluser        SS 009999.99",
                "03 Rolex Watch               selluser        050 100.00",
                "00 selluser        SS 009999.99"
        });

        // Create transaction file and add a serializer
        TransactionFile file = new TransactionFile(TRANSACTIONS_FILE);
        file.addSerializer(TransactionType.LOGIN, new BasicTransaction.Serializer());
        file.addSerializer(TransactionType.ADVERTISE, new AdvertiseTransaction.Serializer());
        file.addSerializer(TransactionType.LOGOUT, new BasicTransaction.Serializer());

        // Open file
        file.open();

        List<Transaction> transactions = file.getTransactions();
        for (Transaction t : transactions) {
            if (t.getType() == TransactionType.LOGIN
                    || t.getType() == TransactionType.LOGOUT) {
                // If the transactions are of type login or logout, ensure that they contain the correct information
                BasicTransaction transaction = (BasicTransaction)t;

                assertEquals(transaction.getUserName(), "selluser");
                assertEquals(transaction.getUserType(), UserType.SELL);
                assertEquals(transaction.getCredits(), 9999.99);
            } else if (t.getType() == TransactionType.ADVERTISE) {
                // If the transaction is of type advertise, check to make sure it contains the correct information
                AdvertiseTransaction transaction = (AdvertiseTransaction)t;

                assertEquals(transaction.getItemName(), "Rolex Watch");
                assertEquals(transaction.getSellerUserName(), "selluser");
                assertEquals(transaction.getDaysToAuction(), 50);
                assertEquals(transaction.getMinBid(), 100.00);
            }
        }

        // Close file
        file.close();
    }
}