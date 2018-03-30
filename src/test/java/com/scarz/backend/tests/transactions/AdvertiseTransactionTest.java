package com.scarz.backend.tests.transactions;

import com.scarz.backend.transactions.AdvertiseTransaction;
import junit.framework.TestCase;

public class AdvertiseTransactionTest extends TestCase {
    public void testGetItemName() {
        AdvertiseTransaction transaction = new AdvertiseTransaction("Rolex Watch", "selluser", 50, 100.00);
        assertEquals("Rolex Watch", transaction.getItemName());
    }

    public void testGetSellerUserName() {
        AdvertiseTransaction transaction = new AdvertiseTransaction("Rolex Watch", "selluser", 50, 100.00);
        assertEquals("selluser", transaction.getSellerUserName());
    }

    public void testGetDaysToAuction() {
        AdvertiseTransaction transaction = new AdvertiseTransaction("Rolex Watch", "selluser", 50, 100.00);
        assertEquals(50, transaction.getDaysToAuction());
    }

    public void testGetMinBid() {
        AdvertiseTransaction transaction = new AdvertiseTransaction("Rolex Watch", "selluser", 50, 100.00);
        assertEquals(100.00, transaction.getMinBid());
    }

    public void testDeserializer() throws Exception {
        AdvertiseTransaction.Serializer serializer = new AdvertiseTransaction.Serializer();
        String serializedTransaction = "03 Rolex Watch               selluser        050 100.00";
        AdvertiseTransaction transaction = serializer.deserialize(serializedTransaction);

        assertEquals("Rolex Watch", transaction.getItemName());
        assertEquals("selluser", transaction.getSellerUserName());
        assertEquals(50, transaction.getDaysToAuction());
        assertEquals(100.00, transaction.getMinBid());
    }
}