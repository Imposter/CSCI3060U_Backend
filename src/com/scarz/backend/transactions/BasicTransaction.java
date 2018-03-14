package com.scarz.backend.transactions;

import com.scarz.backend.utility.ISerializer;

/**
 * Basic transaction containing common transaction information
 */
public class BasicTransaction extends Transaction {
    private String mUserName;
    private int mUserType;
    private double mCredits;

    /**
     * Initializes transaction with transaction type
     * @param type Transaction type
     */
    public BasicTransaction(int type) {
        super(type);
    }

    /**
     * Gets user name
     * @return User name
     */
    public String getUserName() {
        return mUserName;
    }

    /**
     * Gets user type
     * @return User's type
     */
    public int getUserType() {
        return mUserType;
    }

    /**
     * Gets user's credits
     * @return User's credits
     */
    public double getCredits() {
        return mCredits;
    }

    /**
     * Serializer for serializing and deserializing AdvertiseTransaction
     */
    public class Serializer implements ISerializer<BasicTransaction> {
        /**
         * Serializes transaction into a string
         * @param basicTransaction Transaction to serialize
         * @return String representation of transaction
         * @throws UnsupportedOperationException Thrown when serialization is not supported
         */
        @Override
        public String serialize(BasicTransaction basicTransaction) throws UnsupportedOperationException {
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
        public BasicTransaction deserialize(String serializedData) throws UnsupportedOperationException {
            // TODO: Implement
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}