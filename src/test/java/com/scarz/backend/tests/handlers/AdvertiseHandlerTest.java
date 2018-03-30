package com.scarz.backend.tests.handlers;

import com.scarz.backend.*;
import com.scarz.backend.handlers.AdvertiseHandler;
import com.scarz.backend.tests.TestHelper;
import com.scarz.backend.transactions.AdvertiseTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class AdvertiseHandlerTest extends TestCase {
    public void testGetType() {
        AdvertiseHandler handler = new AdvertiseHandler(null, null);
        assertEquals(TransactionType.ADVERTISE, handler.getType());
    }

    public void testGetName() {
        AdvertiseHandler handler = new AdvertiseHandler(null, null);
        assertEquals("advertise", handler.getName());
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
        AdvertiseHandler handler = new AdvertiseHandler(userFile, itemFile);
        Transaction transaction = new AdvertiseTransaction("Potatoes 50kg", "selluser", 10, 120.00);
        assertTrue(handler.handle(transaction));

        // Check if the item was stored
        Item item = itemFile.getItemByUserAndName("selluser", "Potatoes 50kg");
        assertNotNull(item);
        assertEquals("Potatoes 50kg", item.getName());
        assertEquals("selluser", item.getSellerUserName());
        assertEquals(10, item.getDaysToAuction());
        assertEquals(120.00, item.getCurrentBid());

        // Close files
        userFile.close();
        itemFile.close();
    }
}