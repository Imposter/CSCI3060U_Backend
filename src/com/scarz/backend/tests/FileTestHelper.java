package com.scarz.backend.tests;

import java.io.*;
import java.util.ArrayList;

public class FileTestHelper {
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
}
