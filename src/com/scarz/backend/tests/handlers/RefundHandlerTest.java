package com.scarz.backend.tests.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.UserType;
import com.scarz.backend.handlers.RefundHandler;
import com.scarz.backend.transactions.RefundTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class RefundHandlerTest extends TestCase {
    public void testGetType() {
        RefundHandler handler = new RefundHandler(null);
        assertEquals(handler.getType(), TransactionType.REFUND);
    }

    public void testGetName() {
        RefundHandler handler = new RefundHandler(null);
        assertEquals(handler.getName(), "refund");
    }

    public void testHandle() throws Exception {
        // Create and get files
        HandlerTestHelper.createFiles();
        UserFile userFile = HandlerTestHelper.getUserFile();

        // Open files
        userFile.open();

        // Create handler and handle the transaction
        RefundHandler handler = new RefundHandler(userFile);
        Transaction transaction = new RefundTransaction("buyuser", "selluser", 100.00);
        assertTrue(handler.handle(transaction));

        // Check user info
        User seller = userFile.getUserByName("selluser");
        assertNotNull(seller);
        assertEquals(seller.getName(), "selluser");
        assertEquals(seller.getUserType(), UserType.SELL);
        assertEquals(seller.getCredits(), 9999.99);

        User buyer = userFile.getUserByName("buyuser");
        assertNotNull(buyer);
        assertEquals(buyer.getName(), "buyuser");
        assertEquals(buyer.getUserType(), UserType.BUY);
        assertEquals(buyer.getCredits(), 999099.99);

        // Close files
        userFile.close();
    }
}