package com.scarz.backend.transactions;

import com.scarz.backend.Config;
import com.scarz.backend.utility.ISerializer;
import com.scarz.backend.utility.io.MemoryStream;
import com.scarz.backend.utility.io.StringStream;


/**
 * Transaction for refunds containing relevant information about the refund
 */
public class RefundTransaction extends Transaction {
    private String mBuyerUserName;
    private String mSellerUserName;
    private double mCredits;

    /**
     * Initializes transaction with refund information
     */
    public RefundTransaction(String buyerUserName, String sellerUserName, double credits) {
        super(TransactionType.REFUND);

        mBuyerUserName = buyerUserName;
        mSellerUserName = sellerUserName;
        mCredits = credits;
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
    public static class Serializer implements ISerializer<RefundTransaction> {
        /**
         * Serializes transaction into a string
         * @param refundTransaction Transaction to serialize
         * @return String representation of transaction
         * @throws Exception Thrown when serialization is not supported, or has failed
         */
        @Override
        public String serialize(RefundTransaction refundTransaction) throws Exception {
            // Not needed
            throw new UnsupportedOperationException("Not implemented");
        }

        /**
         * Deserializes string into transaction
         * @param serializedData Data to deserialize into a transaction
         * @return Transaction from data
         * @throws Exception Thrown when deserialization is not supported, or has failed
         */
        @Override
        public RefundTransaction deserialize(String serializedData) throws Exception {
            // Create stream from line
            StringStream stream = new StringStream(new MemoryStream(serializedData.getBytes()));

            // Ignore type
            stream.readString(2);
            stream.read();

            // Read buyer name
            String buyerUserName = stream.readString(Config.USERNAME_LENGTH).trim();
            stream.read();

            // Read seller name
            String sellerUserName = stream.readString(Config.USERNAME_LENGTH).trim();
            stream.read();

            // Read credits
            double credits = Double.parseDouble(stream.readString(Config.CREDITS_LENGTH));

            return new RefundTransaction(buyerUserName, sellerUserName, credits);
        }
    }
}
