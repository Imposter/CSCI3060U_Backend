package com.scarz.backend.tests.transactions;

import com.scarz.backend.transactions.BidTransaction;
import junit.framework.TestCase;

public class BidTransactionTest extends TestCase {
    public void testGetItemName() {
        BidTransaction transaction = new BidTransaction("rolex watches", "selluser", "buyuser", 110.00);
        assertEquals(transaction.getSellerUserName(), "selluser");
    }

    public void testGetBuyerUserName() {
        // TODO/NOTE: TEST FAILED: Constructor arguments were backwards (selluser/buyuser)
        BidTransaction transaction = new BidTransaction("rolex watches", "selluser", "buyuser", 110.00);
        assertEquals(transaction.getBuyerUserName(), "buyuser");
    }

    public void testGetSellerUserName() {
        // TODO/NOTE: TEST FAILED: Constructor arguments were backwards (selluser/buyuser)
        BidTransaction transaction = new BidTransaction("rolex watches", "selluser", "buyuser", 110.00);
        assertEquals(transaction.getSellerUserName(), "selluser");
    }

    public void testGetNewBid() {
        BidTransaction transaction = new BidTransaction("rolex watches", "selluser", "buyuser", 110.00);
        assertEquals(transaction.getNewBid(), 110.00);
    }

    public void testDeserializer() throws Exception {
        BidTransaction.Serializer serializer = new BidTransaction.Serializer();
        String serializedTransaction = "04 rolex watches             selluser        buyuser         110.00";
        BidTransaction transaction = serializer.deserialize(serializedTransaction);

        assertEquals(transaction.getItemName(), "rolex watches");
        assertEquals(transaction.getSellerUserName(), "selluser");
        assertEquals(transaction.getBuyerUserName(), "buyuser");
        assertEquals(transaction.getNewBid(), 110.00);
    }
}