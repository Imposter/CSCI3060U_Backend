package com.scarz.backend;

import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.utility.ISerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Daily transactions file used to store transactions applied to the system for the day
 */
public class TransactionFile extends File {
    private Map<Integer, ISerializer<Transaction>> mSerializers;
    private List<Transaction> mTransactions;

    /**
     * Initializes transaction file with specified file path
     * @param name File path
     */
    public TransactionFile(String name) {
        super(name);

        mSerializers = new TreeMap<>();
        mTransactions = new ArrayList<>();
    }

    /**
     * Stores the serializer for the specified transaction type
     * @param type Transaction to which the serializer is used for
     * @param serializer Serializer used to serialize or deserialize transaction
     * @param <TTransaction> Transaction class inheriting from Transaction
     */
    public <TTransaction extends Transaction> void addSerializer(int type, ISerializer<TTransaction> serializer) {
        mSerializers.put(type, (ISerializer<Transaction>)serializer);
    }

    /**
     * Opens transaction file and reads all the transactions into a list of transactions
     * @throws Exception Thrown when an error occurs while reading the file
     */
    public void open() throws Exception {
        // Open in read mode
        openRead();

        // Read all transactions
        List<String> lines = readLines();
        for (String line : lines) {
            // Skip invalid lines
            if (line.isEmpty())
                continue;

            int type = Integer.parseInt(line.substring(0, 2));

            // Find serializer
            ISerializer<Transaction> serializer = mSerializers.get(type);
            if (serializer == null)
                throw new ClassNotFoundException("Serializer for type does not exist");

            // Parse transaction
            Transaction transaction = serializer.deserialize(line);

            // Store transaction
            mTransactions.add(transaction);
        }

        // Close handle
        closeRead();
    }

    /**
     * Returns all of the transactions made of a certain type
     * @return All transactions of the specified type
     */
    public List<Transaction> getTransactions() {
        return mTransactions;
    }
}
