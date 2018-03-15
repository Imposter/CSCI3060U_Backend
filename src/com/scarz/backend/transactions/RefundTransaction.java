package com.scarz.backend.transactions;

import com.scarz.backend.utility.ISerializer;


/**
 * Transaction for refunds containing relevant information about the refund
 */
public class RefundTransaction extends Transaction {
    private String mBuyerUserName;
    private String mSellerUserName;
    private double mCredits;

    /**
     * Initializes transaction with refund type
     * @param type Refund type
     */
    public RefundTransaction (int type) {
        super (type);
    }

    /**
     * Gets buyer's username
     * @return Buyer's username
     */
    public String getBuyerUserName() {
        return mBuyerUserName;
    }

    /**
     * Gets seller's username
     * @return Seller's username
     */
    public String getSellerUserName() {
        return mSellerUserName;
    }

    /**
     * Gets user's credits
     * @return User's credits
     */
    public double getCredits() {
        return mCredits;
    }

    /**
     * Serializer for serializing and deserializing RefundTransaction
     */
    public class Serializer implements ISerializer<RefundTransaction> {
        /**
         * Serializes transaction into a string
         * @param refundTransaction Transaction to serialize
         * @return String representation of transaction
         * @throws UnsupportedOperationException Thrown when serialization is not supported
         */
        @Override
        public String serialize(RefundTransaction refundTransaction) throws UnsupportedOperationException {
            // TODO: Implement
            throw new UnsupportedOperationException("Not implemented");
        }

        /**
         * Deserializes string into transaction
         * @param serializedData Data to deserialize into a transaction
         * @return Transaction from data
         * @throws UnsupportedOperationException Thrown when deserialization is not supported
         */
        @Override
        public RefundTransaction deserialize(String serializedData) throws UnsupportedOperationException {
            // TODO: Implement
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}
