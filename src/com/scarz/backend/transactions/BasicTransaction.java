package com.scarz.backend.transactions;

import com.scarz.backend.Config;
import com.scarz.backend.UserType;
import com.scarz.backend.utility.ISerializer;
import com.scarz.backend.utility.io.MemoryStream;
import com.scarz.backend.utility.io.StringStream;

/**
 * Basic transaction containing common transaction information, used for
 * AddCredit, Create, Delete, Login, and Logout
 */
public class BasicTransaction extends Transaction {
    private String mUserName;
    private int mUserType;
    private double mCredits;

    /**
     * Initializes transaction with transaction type
     * @param type Transaction type
     */
    public BasicTransaction(int type, String userName, int userType, double credits) {
        super(type);

        mUserName = userName;
        mUserType = userType;
        mCredits = credits;
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
     * Serializer for serializing and deserializing BasicTransaction
     */
    public static class Serializer implements ISerializer<BasicTransaction> {
        /**
         * Serializes transaction into a string
         * @param basicTransaction Transaction to serialize
         * @return String representation of transaction
         * @throws Exception Thrown when serialization is not supported, or has failed
         */
        @Override
        public String serialize(BasicTransaction basicTransaction) throws Exception {
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
        public BasicTransaction deserialize(String serializedData) throws Exception {
            // Create stream from line
            StringStream stream = new StringStream(new MemoryStream(serializedData.getBytes()));

            // Read type
            int type = Integer.parseInt(stream.readString(2));
            stream.read();

            // Read username
            String userName = stream.readString(Config.USERNAME_LENGTH).trim();
            stream.read();

            // Read user type
            int userType = UserType.getUserTypeFromString(stream.readString(Config.USER_TYPE_LENGTH));
            stream.read();

            // Read user credits
            double userCredits = Double.parseDouble(stream.readString(Config.CREDITS_LENGTH));

            return new BasicTransaction(type, userName, userType, userCredits);
        }
    }
}