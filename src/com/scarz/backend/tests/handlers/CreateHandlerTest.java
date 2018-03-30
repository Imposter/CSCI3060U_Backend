package com.scarz.backend.tests.handlers;

import com.scarz.backend.*;
import com.scarz.backend.handlers.CreateHandler;
import com.scarz.backend.tests.TestHelper;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class CreateHandlerTest extends TestCase {
    public void testGetType() {
        CreateHandler handler = new CreateHandler(null);
        assertEquals(TransactionType.CREATE, handler.getType());
    }

    public void testGetName() {
        CreateHandler handler = new CreateHandler(null);
        assertEquals("create", handler.getName());
    }

    public void testHandle() throws Exception {
        // Create and get files
        TestHelper.createFiles();
        UserFile userFile = TestHelper.getUserFile();

        // Open files
        userFile.open();

        // Create handler and handle the transaction
        CreateHandler handler = new CreateHandler(userFile);
        Transaction transaction = new BasicTransaction(TransactionType.CREATE, "newuser", UserType.BUY, 100.00);
        assertTrue(handler.handle(transaction));

        // Check if the user was stored
        User user = userFile.getUserByName("newuser");
        assertNotNull(user);
        assertEquals("newuser", user.getName());
        assertEquals(UserType.BUY, user.getUserType());
        assertEquals(100.00, user.getCredits());

        // Close files
        userFile.close();
    }
}