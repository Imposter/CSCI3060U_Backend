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

    public void testAddSerializer() {
        // Create transaction file and add a serializer
        TransactionFile file = TestHelper.getTransactionFile();
        file.addSerializer(TransactionType.LOGIN, new BasicTransaction.Serializer());
    }

    public void testOpen() throws Exception {
        // Write test data to file
        TestHelper.createFiles();

        // Create transaction file and add a serializer
        TransactionFile file = TestHelper.getTransactionFile();
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
        TestHelper.createFiles();

        // Create transaction file and add a serializer
        TransactionFile file = TestHelper.getTransactionFile();
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

                assertEquals("selluser", transaction.getUserName());
                assertEquals(UserType.SELL, transaction.getUserType());
                assertEquals(9999.99, transaction.getCredits());
            } else if (t.getType() == TransactionType.ADVERTISE) {
                // If the transaction is of type advertise, check to make sure it contains the correct information
                AdvertiseTransaction transaction = (AdvertiseTransaction)t;

                assertEquals("Rolex Watch", transaction.getItemName());
                assertEquals("selluser", transaction.getSellerUserName());
                assertEquals(50, transaction.getDaysToAuction());
                assertEquals(100.00, transaction.getMinBid());
            }
        }

        // Close file
        file.close();
    }
}