package com.scarz.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic file class providing input and output in terms of lines
 */
public class File {
    private String mName;
    private boolean mReadMode;
    private boolean mWriteMode;
    private InputStream mInputStream;
    private OutputStream mOutputStream;

    /**
     * Initializes instance with the file name
     * @param name File path
     */
    public File(String name) {
        mName = name;
    }

    /**
     * Closes all file streams
     * @throws IOException Thrown when the file cannot be closed
     */
    public void close() throws IOException {
        closeRead();
        closeWrite();
    }

    /**
     * Reads all the lines in the file
     * @return All the lines read from the file
     * @throws IOException Thrown when file is not able to be read
     * @throws UnsupportedOperationException Thrown when the file is not opened in read mode
     */
    public String[] readLines() throws IOException, UnsupportedOperationException {
        // Check if file is open
        if (!mReadMode)
            throw new UnsupportedOperationException("File is not open in read mode");

        List<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream));
        String line;
        while ((line = reader.readLine()) != null)
            result.add(line);

        return result.toArray(new String[0]);
    }

    /**
     * Writes a line to the file
     * @param line Line to write to file
     * @throws IOException Thrown when the file is not able to be written to
     * @throws UnsupportedOperationException Thrown when the file is not opened in write mode
     */
    public void writeLine(String line) throws IOException, UnsupportedOperationException {
        // Check if file is open
        if (!mWriteMode)
            throw new UnsupportedOperationException("File is not open in write mode");

        // Write line contents
        byte[] bytes = line.getBytes();
        mOutputStream.write(bytes);

        // Write new line
        bytes = System.lineSeparator().getBytes();
        mOutputStream.write(bytes);
    }

    /**
     * Closes file's write stream
     * @throws IOException Thrown when the stream fails to close
     */
    public void closeWrite() throws IOException {
        if (mWriteMode) {
            mOutputStream.close();
            mWriteMode = false;
        }
    }

    /**
     * Closes file's read stream
     * @throws IOException Thrown when the stream fails to close
     */
    public void closeRead() throws IOException {
        if (mReadMode) {
            mInputStream.close();
            mReadMode = false;
        }
    }

    /**
     * Opens the file in read mode
     * @throws IOException Thrown when the file is not able to be opened
     */
    public void openRead() throws IOException {
        if (mReadMode)
            return;

        // Close stream if writing
        closeWrite();

        // Open file
        mInputStream = new FileInputStream(mName);

        mReadMode = true;
    }

    /**
     * Opens the file in write mode
     * @param append Open the file in append mode
     * @throws IOException Thrown when the file is not able to be opened
     */
    public void openWrite(boolean append) throws IOException {
        if (mWriteMode)
            return;

        // Close stream if reading
        closeRead();

        // Open file
        mOutputStream = new FileOutputStream(mName, append);

        mWriteMode = true;
    }
}
