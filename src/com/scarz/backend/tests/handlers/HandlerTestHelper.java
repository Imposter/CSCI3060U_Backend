package com.scarz.backend.tests.handlers;

import com.scarz.backend.ItemFile;
import com.scarz.backend.UserFile;

import java.io.*;

public class HandlerTestHelper {
    public static final String USERS_FILE = "test_current_users.txt";
    public static final String ITEMS_FILE = "test_available_items.txt";

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
                "END"
        });
    }

    public static UserFile getUserFile() {
        return new UserFile(USERS_FILE);
    }

    public static ItemFile getItemFile() {
        return new ItemFile(ITEMS_FILE);
    }
}
