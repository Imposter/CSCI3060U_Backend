package com.scarz.backend.tests.transactions;

import com.scarz.backend.transactions.RefundTransaction;
import junit.framework.TestCase;

public class RefundTransactionTest extends TestCase {
    public void testGetBuyerUserName() {
        RefundTransaction transaction = new RefundTransaction("buyuser", "selluser", 100.00);
        assertEquals(transaction.getBuyerUserName(), "buyuser");
    }

    public void testGetSellerUserName() {
        RefundTransaction transaction = new RefundTransaction("buyuser", "selluser", 100.00);
        assertEquals(transaction.getSellerUserName(), "selluser");
    }

    public void testGetCredits() {
        RefundTransaction transaction = new RefundTransaction("buyuser", "selluser", 100.00);
        assertEquals(transaction.getCredits(), 100.00);
    }

    public void testDeserializer() throws Exception {
        RefundTransaction.Serializer serializer = new RefundTransaction.Serializer();
        String serializedTransaction = "05 buyuser         selluser        000100.00";
        RefundTransaction transaction = serializer.deserialize(serializedTransaction);

        assertEquals(transaction.getBuyerUserName(), "buyuser");
        assertEquals(transaction.getSellerUserName(), "selluser");
        assertEquals(transaction.getCredits(), 100.00);
    }
}