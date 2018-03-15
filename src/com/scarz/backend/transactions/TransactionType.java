package com.scarz.backend.transactions;

/**
 * Transaction type, used to determine type of transaction
 */
public class TransactionType {
    public static final int NONE = -1;
    public static final int LOGIN = 10;
    public static final int LOGOUT = 0;
    public static final int CREATE = 1;
    public static final int DELETE = 2;
    public static final int ADVERTISE = 3;
    public static final int BID = 4;
    public static final int REFUND = 5;
    public static final int ADD_CREDIT = 6;

    /**
     * Converts transaction type to a string
     * @param type Transaction type to convert to string
     * @return  Transaction type in its string representation
     */
    public static String getTransactionTypeString(int type) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }
}