package com.scarz.backend.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;

/**
 * Handles operations for transaction type delete
 */
public class DeleteHandler implements IHandler {
    private UserFile mUserFile;

    /**
     * Initializes handler with required files
     * @param userFile User accounts file
     */
    public DeleteHandler(UserFile userFile) {
        mUserFile = userFile;
    }

    /**
     * Gets transaction type
     * @return Transaction type
     */
    @Override
    public int getType() {
        return TransactionType.DELETE;
    }

    /**
     * Gets transaction type in its string representation
     * @return Transaction type in string form
     */
    @Override
    public String getName() {
        return "delete";
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
            System.out.printf("[%s] User %s does not exist!\r\n", getName(), transaction.getUserName());
            return false;
        }

        // Remove user
        mUserFile.removeUser(user);

        System.out.printf("[%s] Deleted user %s\r\n", getName(), transaction.getUserName());

        return true;
    }
}
