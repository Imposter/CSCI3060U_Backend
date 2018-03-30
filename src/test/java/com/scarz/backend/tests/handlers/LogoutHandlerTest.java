package com.scarz.backend.tests.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.UserType;
import com.scarz.backend.handlers.LogoutHandler;
import com.scarz.backend.tests.TestHelper;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class LogoutHandlerTest extends TestCase {
    public void testGetType() {
        LogoutHandler handler = new LogoutHandler(null);
        assertEquals(TransactionType.LOGOUT, handler.getType());
    }

    public void testGetName() {
        LogoutHandler handler = new LogoutHandler(null);
        assertEquals("logout", handler.getName());
    }

    public void testHandle() throws Exception {
        // Create and get files
        TestHelper.createFiles();
        UserFile userFile = TestHelper.getUserFile();

        // Open files
        userFile.open();

        // Create handler and handle the transaction
        LogoutHandler handler = new LogoutHandler(userFile);
        Transaction transaction = new BasicTransaction(TransactionType.LOGOUT, "selluser", UserType.SELL, 10099.99);
        assertTrue(handler.handle(transaction));

        // Check user info
        User user = userFile.getUserByName("selluser");
        assertNotNull(user);
        assertEquals("selluser", user.getName());
        assertEquals(UserType.SELL, user.getUserType());
        assertEquals(10099.99, user.getCredits());

        // Close files
        userFile.close();
    }
}