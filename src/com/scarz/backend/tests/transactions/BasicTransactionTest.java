package com.scarz.backend.tests.transactions;

import com.scarz.backend.UserType;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class BasicTransactionTest extends TestCase {
    public void testGetUserName() {
        BasicTransaction transaction = new BasicTransaction(TransactionType.LOGIN, "selluser", UserType.SELL, 9999.99);
        assertEquals(transaction.getUserName(), "selluser");
    }

    public void testGetUserType() {
        BasicTransaction transaction = new BasicTransaction(TransactionType.LOGIN, "selluser", UserType.SELL, 9999.99);
        assertEquals(transaction.getUserType(), UserType.SELL);
    }

    public void testGetCredits() {
        BasicTransaction transaction = new BasicTransaction(TransactionType.LOGIN, "selluser", UserType.SELL, 9999.99);
        assertEquals(transaction.getCredits(), 9999.99);
    }

    public void testDeserializer() throws Exception {
        BasicTransaction.Serializer serializer = new BasicTransaction.Serializer();
        String serializedTransaction = "10 selluser        SS 009999.99";
        BasicTransaction transaction = serializer.deserialize(serializedTransaction);

        assertEquals(transaction.getUserName(), "selluser");
        assertEquals(transaction.getUserType(), UserType.SELL);
        assertEquals(transaction.getCredits(), 9999.99);
    }
}