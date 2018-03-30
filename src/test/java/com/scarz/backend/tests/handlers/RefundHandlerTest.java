package com.scarz.backend.tests.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.UserType;
import com.scarz.backend.handlers.RefundHandler;
import com.scarz.backend.tests.TestHelper;
import com.scarz.backend.transactions.RefundTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class RefundHandlerTest extends TestCase {
    public void testGetType() {
        RefundHandler handler = new RefundHandler(null);
        assertEquals(TransactionType.REFUND, handler.getType());
    }

    public void testGetName() {
        RefundHandler handler = new RefundHandler(null);
        assertEquals("refund", handler.getName());
    }

    public void testHandle() throws Exception {
        // Create and get files
        TestHelper.createFiles();
        UserFile userFile = TestHelper.getUserFile();

        // Open files
        userFile.open();

        // Create handler and handle the transaction
        RefundHandler handler = new RefundHandler(userFile);
        Transaction transaction = new RefundTransaction("buyuser", "selluser", 100.00);
        assertTrue(handler.handle(transaction));

        // Check user info
        User seller = userFile.getUserByName("selluser");
        assertNotNull(seller);
        assertEquals("selluser", seller.getName());
        assertEquals(UserType.SELL, seller.getUserType());
        assertEquals(9999.99, seller.getCredits());

        User buyer = userFile.getUserByName("buyuser");
        assertNotNull(buyer);
        assertEquals("buyuser", buyer.getName());
        assertEquals(UserType.BUY, buyer.getUserType());
        assertEquals(999099.99, buyer.getCredits());

        // Close files
        userFile.close();
    }
}