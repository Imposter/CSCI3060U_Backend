package com.scarz.backend.tests.handlers;

import com.scarz.backend.Item;
import com.scarz.backend.ItemFile;
import com.scarz.backend.UserFile;
import com.scarz.backend.handlers.BidHandler;
import com.scarz.backend.tests.TestHelper;
import com.scarz.backend.transactions.BidTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class BidHandlerTest extends TestCase {
    public void testGetType() {
        BidHandler handler = new BidHandler(null, null);
        assertEquals(TransactionType.BID, handler.getType());
    }

    public void testGetName() {
        BidHandler handler = new BidHandler(null, null);
        assertEquals("bid", handler.getName());
    }

    public void testHandle() throws Exception {
        // Create and get files
        TestHelper.createFiles();
        UserFile userFile = TestHelper.getUserFile();
        ItemFile itemFile = TestHelper.getItemFile();

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
        assertEquals("Rolex Watch", item.getName());
        assertEquals("selluser", item.getSellerUserName());
        assertEquals("buyuser", item.getBidderUserName());
        assertEquals(42, item.getDaysToAuction());
        assertEquals(150.00, item.getCurrentBid());

        // Close files
        userFile.close();
        itemFile.close();
    }
}