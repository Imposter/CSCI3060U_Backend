package com.scarz.backend.tests.transactions;

import com.scarz.backend.transactions.RefundTransaction;
import junit.framework.TestCase;

public class RefundTransactionTest extends TestCase {
    public void testGetBuyerUserName() {
        RefundTransaction transaction = new RefundTransaction("buyuser", "selluser", 100.00);
        assertEquals("buyuser", transaction.getBuyerUserName());
    }

    public void testGetSellerUserName() {
        RefundTransaction transaction = new RefundTransaction("buyuser", "selluser", 100.00);
        assertEquals("selluser", transaction.getSellerUserName());
    }

    public void testGetCredits() {
        RefundTransaction transaction = new RefundTransaction("buyuser", "selluser", 100.00);
        assertEquals(100.00, transaction.getCredits());
    }

    public void testDeserializer() throws Exception {
        RefundTransaction.Serializer serializer = new RefundTransaction.Serializer();
        String serializedTransaction = "05 buyuser         selluser        000100.00";
        RefundTransaction transaction = serializer.deserialize(serializedTransaction);

        assertEquals("buyuser", transaction.getBuyerUserName());
        assertEquals("selluser", transaction.getSellerUserName());
        assertEquals(100.00, transaction.getCredits());
    }
}