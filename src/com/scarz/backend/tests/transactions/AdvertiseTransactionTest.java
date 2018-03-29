package com.scarz.backend.tests.transactions;

import com.scarz.backend.transactions.AdvertiseTransaction;
import junit.framework.TestCase;

public class AdvertiseTransactionTest extends TestCase {
    public void testGetItemName() {
        AdvertiseTransaction transaction = new AdvertiseTransaction("Rolex Watch", "selluser", 50, 100.00);
        assertEquals(transaction.getItemName(), "Rolex Watch");
    }

    public void testGetSellerUserName() {
        AdvertiseTransaction transaction = new AdvertiseTransaction("Rolex Watch", "selluser", 50, 100.00);
        assertEquals(transaction.getSellerUserName(), "selluser");
    }

    public void testGetDaysToAuction() {
        AdvertiseTransaction transaction = new AdvertiseTransaction("Rolex Watch", "selluser", 50, 100.00);
        assertEquals(transaction.getDaysToAuction(), 50);
    }

    public void testGetMinBid() {
        AdvertiseTransaction transaction = new AdvertiseTransaction("Rolex Watch", "selluser", 50, 100.00);
        assertEquals(transaction.getMinBid(), 100.00);
    }

    public void testDeserializer() throws Exception {
        AdvertiseTransaction.Serializer serializer = new AdvertiseTransaction.Serializer();
        String serializedTransaction = "03 Rolex Watch               selluser        050 100.00";
        AdvertiseTransaction transaction = serializer.deserialize(serializedTransaction);

        assertEquals(transaction.getItemName(), "Rolex Watch");
        assertEquals(transaction.getSellerUserName(), "selluser");
        assertEquals(transaction.getDaysToAuction(), 50);
        assertEquals(transaction.getMinBid(), 100.00);
    }
}