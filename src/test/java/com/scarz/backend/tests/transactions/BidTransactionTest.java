package com.scarz.backend.tests.transactions;

import com.scarz.backend.transactions.BidTransaction;
import junit.framework.TestCase;

public class BidTransactionTest extends TestCase {
    public void testGetItemName() {
        BidTransaction transaction = new BidTransaction("rolex watches", "selluser", "buyuser", 110.00);
        assertEquals("selluser", transaction.getSellerUserName());
    }

    public void testGetBuyerUserName() {
        // TODO/NOTE: TEST FAILED: Constructor arguments were backwards (selluser/buyuser)
        BidTransaction transaction = new BidTransaction("rolex watches", "selluser", "buyuser", 110.00);
        assertEquals("buyuser", transaction.getBuyerUserName());
    }

    public void testGetSellerUserName() {
        // TODO/NOTE: TEST FAILED: Constructor arguments were backwards (selluser/buyuser)
        BidTransaction transaction = new BidTransaction("rolex watches", "selluser", "buyuser", 110.00);
        assertEquals("selluser", transaction.getSellerUserName());
    }

    public void testGetNewBid() {
        BidTransaction transaction = new BidTransaction("rolex watches", "selluser", "buyuser", 110.00);
        assertEquals(110.00, transaction.getNewBid());
    }

    public void testDeserializer() throws Exception {
        BidTransaction.Serializer serializer = new BidTransaction.Serializer();
        String serializedTransaction = "04 rolex watches             selluser        buyuser         110.00";
        BidTransaction transaction = serializer.deserialize(serializedTransaction);

        assertEquals("rolex watches", transaction.getItemName());
        assertEquals("selluser", transaction.getSellerUserName());
        assertEquals("buyuser", transaction.getBuyerUserName());
        assertEquals(110.00, transaction.getNewBid());
    }
}