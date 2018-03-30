package com.scarz.backend.tests;

import com.scarz.backend.ItemFile;
import com.scarz.backend.TransactionFile;
import com.scarz.backend.UserFile;

import java.io.*;
import java.util.ArrayList;

public class TestHelper {
    public static final String USERS_FILE = "test_current_users.txt";
    public static final String ITEMS_FILE = "test_available_items.txt";
    public static final String TRANSACTIONS_FILE = "test_transactions.txt";

    public static String[] readLinesFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;
        ArrayList<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines.toArray(new String[0]);
    }

    public static void writeLinesToFile(String fileName, String[] lines) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        for (String line : lines) {
            writer.write(line + System.lineSeparator());
        }
        writer.close();
    }

    public static void createFiles() throws IOException {
        // Create user file
        writeLinesToFile(USERS_FILE, new String[]{
                "admin           AA 001000.00",
                "fulluser        FS 000000.00",
                "selluser        SS 010099.99",
                "buyuser         BS 998999.99",
                "deleteuser      BS 000000.00",
                "badseller       BS 000030.00",
                "END"
        });

        // Create items file
        writeLinesToFile(ITEMS_FILE, new String[]{
                "Rolex Watch               selluser                        042 100.00",
                "Rolex Watches             selluser                        003 995.99",
                "END"
        });

        // Create transactions file
        writeLinesToFile(TRANSACTIONS_FILE, new String[] {
                "10 selluser        SS 009999.99",
                "03 Rolex Watch               selluser        050 100.00",
                "00 selluser        SS 009999.99"
        });
    }

    public static UserFile getUserFile() {
        return new UserFile(USERS_FILE);
    }

    public static ItemFile getItemFile() {
        return new ItemFile(ITEMS_FILE);
    }

    public static TransactionFile getTransactionFile() {
        return new TransactionFile(TRANSACTIONS_FILE);
    }
}
