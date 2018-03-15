package com.scarz.backend.handlers;

import com.scarz.backend.ItemFile;
import com.scarz.backend.UserFile;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.BidTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;

/**
 * Handles operations for transaction type bid
 */
public class BidHandler implements IHandler {
    private UserFile mUserFile;
    private ItemFile mItemFile;

    /**
     * Initializes handler with required files
     * @param userFile User accounts file
     * @param itemFile Available items file
     */
    public BidHandler(UserFile userFile, ItemFile itemFile) {
        mUserFile = userFile;
        mItemFile = itemFile;
    }

    /**
     * Gets transaction type
     * @return Transaction type
     */
    @Override
    public int getType() {
        return TransactionType.BID;
    }

    /**
     * Gets transaction type in its string representation
     * @return Transaction type in string form
     */
    @Override
    public String getName() {
        return "bid";
    }

    /**
     * Handles the transaction and applies changes to user file and item file
     * @param t Transaction to apply
     * @return Whether the transaction was applied successfully or not
     */
    @Override
    public boolean handle(Transaction t) {
        BidTransaction transaction = (BidTransaction)t;

        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
