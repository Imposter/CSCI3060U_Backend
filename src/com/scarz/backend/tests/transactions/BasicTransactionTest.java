package com.scarz.backend.tests.transactions;

import com.scarz.backend.UserType;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class BasicTransactionTest extends TestCase {
    public void testGetUserName() {
        BasicTransaction transaction = new BasicTransaction(TransactionType.LOGIN, "selluser", UserType.SELL, 9999.99);
        assertEquals("selluser", transaction.getUserName());
    }

    public void testGetUserType() {
        BasicTransaction transaction = new BasicTransaction(TransactionType.LOGIN, "selluser", UserType.SELL, 9999.99);
        assertEquals(UserType.SELL, transaction.getUserType());
    }

    public void testGetCredits() {
        BasicTransaction transaction = new BasicTransaction(TransactionType.LOGIN, "selluser", UserType.SELL, 9999.99);
        assertEquals(9999.99, transaction.getCredits());
    }

    public void testDeserializer() throws Exception {
        BasicTransaction.Serializer serializer = new BasicTransaction.Serializer();
        String serializedTransaction = "10 selluser        SS 009999.99";
        BasicTransaction transaction = serializer.deserialize(serializedTransaction);

        assertEquals("selluser", transaction.getUserName());
        assertEquals(UserType.SELL, transaction.getUserType());
        assertEquals(9999.99, transaction.getCredits());
    }
}