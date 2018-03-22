package com.scarz.backend.utility.io;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class StringStream {
    private IStream mStream;
    private int mPeekByte;

    private int readBytes(byte[] buffer, int length) throws IOException {
        if (mPeekByte != -1) {
            buffer[0] = (byte)mPeekByte;
            mPeekByte = -1;
            return mStream.read(buffer, 1, length - 1) + 1;
        }

        return mStream.read(buffer, 0, length);
    }

    public int peek() throws IOException {
        if (mPeekByte != -1)
            return mPeekByte;

        byte[] b = new byte[1];
        if (mStream.read(b, 0, b.length) > 0) {
            mPeekByte = b[0];
            return mPeekByte;
        }

        return -1;
    }

    public int read() throws IOException {
        byte[] b = new byte[1];
        return readBytes(b, b.length) < 0 ? -1 : b[0];
    }

    public void write(byte obj) throws IOException {
        byte[] b = { obj };
        mStream.write(b, 0, b.length);
    }

    public boolean read(byte[] buffer, int length) throws IOException {
        return mStream.read(buffer, 0, length) == length;
    }

    public void write(byte[] buffer, int length) throws IOException {
        mStream.write(buffer, 0, length);
    }

    public void write(String str, String encoding) throws IOException {
        byte[] bytes = str.getBytes(encoding);
        mStream.write(bytes, 0, bytes.length);
    }

    public void write(String str) throws IOException {
        write(str, "UTF-8");
    }

    public StringStream(IStream stream) {
        mStream = stream;
        mPeekByte = -1;
    }

    public IStream getStream() {
        return mStream;
    }

    public String readString(int length, String encoding) throws TimeoutException, IOException {
        // Calculate real length with the specified encoding
        byte[] tempBuffer = "A".getBytes(encoding);
        length *= tempBuffer.length;

        byte[] buffer = new byte[length];
        if (!read(buffer, buffer.length))
            throw new TimeoutException("Unable to read data");

        return new String(buffer, encoding);

    }

    public String readString(int length) throws TimeoutException, IOException {
        return readString(length, "UTF-8");
    }

    public void writeString(String obj, String encoding) throws IOException {
        byte[] buffer = obj.getBytes(encoding);
        write(buffer, buffer.length);
    }
}
