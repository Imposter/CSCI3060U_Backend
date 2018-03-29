package com.scarz.backend.tests.handlers;

import com.scarz.backend.Item;
import com.scarz.backend.ItemFile;
import com.scarz.backend.UserFile;
import com.scarz.backend.handlers.BidHandler;
import com.scarz.backend.transactions.BidTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class BidHandlerTest extends TestCase {
    public void testGetType() {
        BidHandler handler = new BidHandler(null, null);
        assertEquals(handler.getType(), TransactionType.BID);
    }

    public void testGetName() {
        BidHandler handler = new BidHandler(null, null);
        assertEquals(handler.getName(), "bid");
    }

    public void testHandle() throws Exception {
        // Create and get files
        HandlerTestHelper.createFiles();
        UserFile userFile = HandlerTestHelper.getUserFile();
        ItemFile itemFile = HandlerTestHelper.getItemFile();

        // Open files
        userFile.open();
        itemFile.open();

        // Create handler and handle the transaction
        BidHandler handler = new BidHandler(userFile, itemFile);
        Transaction transaction = new BidTransaction("Rolex Watch", "selluser", "buyuser", 150.00);
        assertTrue(handler.handle(transaction));

        // Check if the bid was successful
        Item item = itemFile.getItemByUserAndName("selluser", "Rolex Watch");
        assertNotNull(item);
        assertEquals(item.getName(), "Rolex Watch");
        assertEquals(item.getSellerUserName(), "selluser");
        assertEquals(item.getBidderUserName(), "buyuser");
        assertEquals(item.getDaysToAuction(), 42);
        assertEquals(item.getCurrentBid(), 150.00);

        // Close files
        userFile.close();
        itemFile.close();
    }
}