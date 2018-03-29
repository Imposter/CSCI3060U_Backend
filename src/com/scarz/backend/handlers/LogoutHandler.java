package com.scarz.backend.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;

/**
 * Handles operations for transaction type logout
 */
public class LogoutHandler implements IHandler {
    private UserFile mUserFile;

    /**
     * Initializes handler with required files
     * @param userFile User accounts file
     */
    public LogoutHandler(UserFile userFile) {
        mUserFile = userFile;
    }

    /**
     * Gets transaction type
     * @return Transaction type
     */
    @Override
    public int getType() {
        return TransactionType.LOGOUT;
    }

    /**
     * Gets transaction type in its string representation
     * @return Transaction type in string form
     */
    @Override
    public String getName() {
        return "logout";
    }

    /**
     * Handles the transaction and applies changes to user file and item file
     * @param t Transaction to apply
     * @return Whether the transaction was applied successfully or not
     */
    @Override
    public boolean handle(Transaction t) {
        BasicTransaction transaction = (BasicTransaction)t;

        // Get user
        User user = mUserFile.getUserByName(transaction.getUserName());

        // Check if user exists
        if (user == null) {
            System.out.printf("ERROR: [%s] User %s does not exist!\r\n", getName(), transaction.getUserName());
            return false;
        }

        // Set credits
        user.setCredits(transaction.getCredits());

        System.out.printf("[%s] User %s logged out!\r\n", getName(), user.getName());

        return true;
    }
}
