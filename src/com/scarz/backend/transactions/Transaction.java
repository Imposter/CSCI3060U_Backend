package com.scarz.backend.transactions;

/**
 * Used for storing and interpreting transaction information
 */
public class Transaction {
    private int mType;

    /**
     * Initialize transaction with its type
     * @param type Transaction type
     */
    public Transaction(int type) {
        mType = type;
    }

    /**
     * Returns type of transaction
     * @return Transaction type
     */
    public int getType() {
        return mType;
    }
}
