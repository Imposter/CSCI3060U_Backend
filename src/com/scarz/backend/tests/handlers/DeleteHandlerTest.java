package com.scarz.backend.tests.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.UserType;
import com.scarz.backend.handlers.DeleteHandler;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class DeleteHandlerTest extends TestCase {
    public void testGetType() {
        DeleteHandler handler = new DeleteHandler(null);
        assertEquals(handler.getType(), TransactionType.DELETE);
    }

    public void testGetName() {
        DeleteHandler handler = new DeleteHandler(null);
        assertEquals(handler.getName(), "delete");
    }

    public void testHandle() throws Exception {
        // Create and get files
        HandlerTestHelper.createFiles();
        UserFile userFile = HandlerTestHelper.getUserFile();

        // Open files
        userFile.open();

        // Create handler and handle the transaction
        DeleteHandler handler = new DeleteHandler(userFile);
        Transaction transaction = new BasicTransaction(TransactionType.DELETE, "selluser", UserType.SELL, 10099.99);
        assertTrue(handler.handle(transaction));

        // Check if the user was deleted
        User user = userFile.getUserByName("selluser");
        assertNull(user);

        // Close files
        userFile.close();
    }
}