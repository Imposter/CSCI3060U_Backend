package com.scarz.backend.tests.handlers;

import com.scarz.backend.*;
import com.scarz.backend.handlers.AdvertiseHandler;
import com.scarz.backend.transactions.AdvertiseTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class AdvertiseHandlerTest extends TestCase {
    public void testGetType() {
        AdvertiseHandler handler = new AdvertiseHandler(null, null);
        assertEquals(handler.getType(), TransactionType.ADVERTISE);
    }

    public void testGetName() {
        AdvertiseHandler handler = new AdvertiseHandler(null, null);
        assertEquals(handler.getName(), "advertise");
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
        AdvertiseHandler handler = new AdvertiseHandler(userFile, itemFile);
        Transaction transaction = new AdvertiseTransaction("Potatoes 50kg", "selluser", 10, 120.00);
        assertTrue(handler.handle(transaction));

        // Check if the item was stored
        Item item = itemFile.getItemByUserAndName("selluser", "Potatoes 50kg");
        assertNotNull(item);
        assertEquals(item.getName(), "Potatoes 50kg");
        assertEquals(item.getSellerUserName(), "selluser");
        assertEquals(item.getDaysToAuction(), 10);
        assertEquals(item.getCurrentBid(), 120.00);

        // Close files
        userFile.close();
        itemFile.close();
    }
}