package com.scarz.backend.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.RefundTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;

/**
 * Handles operations for transaction type refund
 */
public class RefundHandler implements IHandler {
    private UserFile mUserFile;

    /**
     * Initializes handler with required files
     * @param userFile User accounts file
     */
    public RefundHandler(UserFile userFile) {
        mUserFile = userFile;
    }

    /**
     * Gets transaction type
     * @return Transaction type
     */
    @Override
    public int getType() {
        return TransactionType.REFUND;
    }

    /**
     * Gets transaction type in its string representation
     * @return Transaction type in string form
     */
    @Override
    public String getName() {
        return "refund";
    }

    /**
     * Handles the transaction and applies changes to user file and item file
     * @param t Transaction to apply
     * @return Whether the transaction was applied successfully or not
     */
    @Override
    public boolean handle(Transaction t) {
        RefundTransaction transaction = (RefundTransaction)t;

        // Get users
        User seller = mUserFile.getUserByName(transaction.getSellerUserName());
        User buyer = mUserFile.getUserByName(transaction.getBuyerUserName());

        // Check if seller exists
        if (seller == null) {
            System.out.printf("ERROR: [%s] Seller %s does not exist!\r\n", getName(), transaction.getSellerUserName());
            return false;
        }

        // Check if buyer exists
        if (buyer == null) {
            System.out.printf("ERROR: [%s] Buyer %s does not exist!\r\n", getName(), transaction.getBuyerUserName());
            return false;
        }

        // Update credits
        seller.setCredits(seller.getCredits() - transaction.getCredits());
        buyer.setCredits(buyer.getCredits() - transaction.getCredits());

        System.out.printf("[%s] Refund given user %s by %s\r\n", getName(), transaction.getBuyerUserName(),
                transaction.getSellerUserName());

        return true;
    }
}
