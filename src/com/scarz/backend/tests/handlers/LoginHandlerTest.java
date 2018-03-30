package com.scarz.backend.tests.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.UserType;
import com.scarz.backend.handlers.LoginHandler;
import com.scarz.backend.tests.TestHelper;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class LoginHandlerTest extends TestCase {
    public void testGetType() {
        LoginHandler handler = new LoginHandler(null);
        assertEquals(TransactionType.LOGIN, handler.getType());
    }

    public void testGetName() {
        LoginHandler handler = new LoginHandler(null);
        assertEquals("login", handler.getName());
    }

    public void testHandle() throws Exception {
        // Create and get files
        TestHelper.createFiles();
        UserFile userFile = TestHelper.getUserFile();

        // Open files
        userFile.open();

        // Create handler and handle the transaction
        LoginHandler handler = new LoginHandler(userFile);
        Transaction transaction = new BasicTransaction(TransactionType.LOGIN, "selluser", UserType.SELL, 10099.99);
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