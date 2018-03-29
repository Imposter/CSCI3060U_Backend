package com.scarz.backend.tests.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.UserType;
import com.scarz.backend.handlers.AddCreditHandler;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;
import junit.framework.TestCase;

public class AddCreditHandlerTest extends TestCase {
    public void testGetType() {
        AddCreditHandler handler = new AddCreditHandler(null);
        assertEquals(handler.getType(), TransactionType.ADD_CREDIT);
    }

    public void testGetName() {
        AddCreditHandler handler = new AddCreditHandler(null);
        assertEquals(handler.getName(), "addcredit");
    }

    public void testHandle() throws Exception {
        // Create and get files
        HandlerTestHelper.createFiles();
        UserFile userFile = HandlerTestHelper.getUserFile();

        // Open user file
        userFile.open();

        // Create handler and handle the transaction
        AddCreditHandler handler = new AddCreditHandler(userFile);
        Transaction transaction = new BasicTransaction(TransactionType.ADD_CREDIT, "admin", UserType.ADMIN, 1200.00);
        assertTrue(handler.handle(transaction));

        // Check if the user's credits were updated
        User user = userFile.getUserByName("admin");
        assertEquals(user.getCredits(), 1200.00);

        // Close user file
        userFile.close();
    }
}