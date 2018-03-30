package com.scarz.backend.tests;

import com.scarz.backend.Item;
import com.scarz.backend.ItemFile;
import junit.framework.TestCase;

import java.util.List;

public class ItemFileTest extends TestCase {
    public void testOpen() throws Exception {
        // Create items file
        TestHelper.createFiles();

        // Open
        ItemFile file = TestHelper.getItemFile();
        file.open();
    }

    public void testClose() throws Exception {
        // Create items file
        TestHelper.createFiles();

        // Open
        ItemFile file = TestHelper.getItemFile();
        file.open();

        // Close
        file.close();
    }

    public void testGetItems() throws Exception {
        // Create items file
        TestHelper.createFiles();

        // Open
        ItemFile file = TestHelper.getItemFile();
        file.open();

        List<Item> items = file.getItems();
        assertEquals(items.size(), 2);

        // Ensure each item is by selluser
        for (Item item : items) {
            assertEquals("selluser", item.getSellerUserName());
        }

        // Close
        file.close();
    }

    public void testGetItemByUserAndName() throws Exception {
        // Create items file
        TestHelper.createFiles();

        // Open
        ItemFile file = TestHelper.getItemFile();
        file.open();

        Item item = file.getItemByUserAndName("selluser", "Rolex Watch");
        assertNotNull(item);

        assertEquals("Rolex Watch", item.getName());
        assertEquals("selluser", item.getSellerUserName());
        assertEquals("", item.getBidderUserName());
        assertEquals(42, item.getDaysToAuction());
        assertEquals(100.00, item.getCurrentBid());
        assertTrue(!item.isNewItem());

        // Close
        file.close();
    }

    public void testAddItem() throws Exception {
        // Create items file
        TestHelper.createFiles();

        // Open
        ItemFile file = TestHelper.getItemFile();
        file.open();

        file.addItem(new Item("Potatoes 100kg", "selluser", "", 5, 350.00, true));

        Item item = file.getItemByUserAndName("selluser", "Potatoes 100kg");
        assertNotNull(item);

        assertEquals("Potatoes 100kg", item.getName());
        assertEquals("selluser", item.getSellerUserName());
        assertEquals("", item.getBidderUserName());
        assertEquals(5, item.getDaysToAuction());
        assertEquals(350.00, item.getCurrentBid());
        assertTrue(item.isNewItem());

        // Close
        file.close();
    }

    public void testRemoveItem() throws Exception {
        // Create items file
        TestHelper.createFiles();

        // Open
        ItemFile file = TestHelper.getItemFile();
        file.open();

        Item item = file.getItemByUserAndName("selluser", "Rolex Watch");
        assertNotNull(item);

        file.removeItem(item);
        item = file.getItemByUserAndName("selluser", "Rolex Watch");
        assertNull(item);

        // Close
        file.close();
    }
}