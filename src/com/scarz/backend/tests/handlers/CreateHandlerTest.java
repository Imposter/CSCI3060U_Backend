package com.scarz.backend.tests.handlers;

import com.scarz.backend.*;
import com.scarz.backend.handlers.CreateHandler;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class CreateHandlerTest extends TestCase {
    public void testGetType() {
        CreateHandler handler = new CreateHandler(null);
        assertEquals(handler.getType(), TransactionType.CREATE);
    }

    public void testGetName() {
        CreateHandler handler = new CreateHandler(null);
        assertEquals(handler.getName(), "create");
    }

    public void testHandle() throws Exception {
        // Create and get files
        HandlerTestHelper.createFiles();
        UserFile userFile = HandlerTestHelper.getUserFile();

        // Open files
        userFile.open();

        // Create handler and handle the transaction
        CreateHandler handler = new CreateHandler(userFile);
        Transaction transaction = new BasicTransaction(TransactionType.CREATE, "newuser", UserType.BUY, 100.00);
        assertTrue(handler.handle(transaction));

        // Check if the user was stored
        User user = userFile.getUserByName("newuser");
        assertNotNull(user);
        assertEquals(user.getName(), "newuser");
        assertEquals(user.getUserType(), UserType.BUY);
        assertEquals(user.getCredits(), 100.00);

        // Close files
        userFile.close();
    }
}