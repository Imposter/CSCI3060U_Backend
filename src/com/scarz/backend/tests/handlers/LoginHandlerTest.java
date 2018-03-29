package com.scarz.backend.tests.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.UserType;
import com.scarz.backend.handlers.LoginHandler;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class LoginHandlerTest extends TestCase {
    public void testGetType() {
        LoginHandler handler = new LoginHandler(null);
        assertEquals(handler.getType(), TransactionType.LOGIN);
    }

    public void testGetName() {
        LoginHandler handler = new LoginHandler(null);
        assertEquals(handler.getName(), "login");
    }

    public void testHandle() throws Exception {
        // Create and get files
        HandlerTestHelper.createFiles();
        UserFile userFile = HandlerTestHelper.getUserFile();

        // Open files
        userFile.open();

        // Create handler and handle the transaction
        LoginHandler handler = new LoginHandler(userFile);
        Transaction transaction = new BasicTransaction(TransactionType.LOGIN, "selluser", UserType.SELL, 10099.99);
        assertTrue(handler.handle(transaction));

        // Check user info
        User user = userFile.getUserByName("selluser");
        assertNotNull(user);
        assertEquals(user.getName(), "selluser");
        assertEquals(user.getUserType(), UserType.SELL);
        assertEquals(user.getCredits(), 10099.99);

        // Close files
        userFile.close();
    }
}