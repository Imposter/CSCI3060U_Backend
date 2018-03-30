package com.scarz.backend.tests;

import com.scarz.backend.File;
import junit.framework.TestCase;

import java.io.IOException;

public class FileTest extends TestCase {

    public void testClose() throws IOException {
        File file = new File("test_close.txt");
        file.openWrite(false);
        file.close();
    }

    public void testReadLines() throws IOException {
        TestHelper.writeLinesToFile("test_read_lines.txt", new String[] {
                "Hello world!"
        });

        File file = new File("test_read_lines.txt");
        file.openRead();
        String[] lines = file.readLines();
        file.closeWrite();

        assertEquals(1, lines.length);
        assertEquals("Hello world!", lines[0]);
    }

    public void testWriteLine() throws IOException {
        File file = new File("test_write_line.txt");
        file.openWrite(false);
        file.writeLine("Hello world!");
        file.closeWrite();

        String[] lines = TestHelper.readLinesFromFile("test_write_line.txt");
        assertEquals(1, lines.length);
        assertEquals("Hello world!", lines[0]);
    }

    public void testCloseWrite() throws IOException {
        File file = new File("test_close_write.txt");
        file.openWrite(false);
        file.closeWrite();
    }

    public void testCloseRead() throws IOException {
        TestHelper.writeLinesToFile("test_close_read.txt", new String[] {
                "Hello world!"
        });

        File file = new File("test_close_read.txt");
        file.openRead();
        file.closeRead();
    }

    public void testOpenRead() throws IOException {
        TestHelper.writeLinesToFile("test_open_read.txt", new String[] {
                "Hello world!"
        });

        File file = new File("test_open_read.txt");
        file.openRead();
        file.closeRead();
    }

    public void testOpenWrite() throws IOException {
        File file = new File("test_open_write.txt");
        file.openWrite(false);
        file.closeWrite();
    }
}