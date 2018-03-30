package com.scarz.backend.tests;

import com.scarz.backend.Item;
import com.scarz.backend.ItemFile;
import junit.framework.TestCase;

import java.util.List;

public class ItemFileTest extends TestCase {
    public static final String ITEMS_FILE = "test_available_items.txt";

    public void testOpen() throws Exception {
        // Create items file
        FileTestHelper.writeLinesToFile(ITEMS_FILE, new String[]{
                "Rolex Watch               selluser                        042 100.00",
                "END"
        });

        // Open
        ItemFile file = new ItemFile(ITEMS_FILE);
        file.open();
    }

    public void testClose() throws Exception {
        // Create items file
        FileTestHelper.writeLinesToFile(ITEMS_FILE, new String[]{
                "Rolex Watch               selluser                        042 100.00",
                "END"
        });

        // Open
        ItemFile file = new ItemFile(ITEMS_FILE);
        file.open();

        // Close
        file.close();
    }

    public void testGetItems() throws Exception {
        // Create items file
        FileTestHelper.writeLinesToFile(ITEMS_FILE, new String[]{
                "Rolex Watch               selluser                        042 100.00",
                "Rolex Watches             selluser                        003 995.99",
                "END"
        });

        // Open
        ItemFile file = new ItemFile(ITEMS_FILE);
        file.open();

        List<Item> items = file.getItems();
        assertEquals(items.size(), 2);

        // Ensure each item is by selluser
        for (Item item : items) {
            assertEquals(item.getSellerUserName(), "selluser");
        }

        // Close
        file.close();
    }

    public void testGetItemByUserAndName() throws Exception {
        // Create items file
        FileTestHelper.writeLinesToFile(ITEMS_FILE, new String[]{
                "Rolex Watch               selluser                        042 100.00",
                "END"
        });

        // Open
        ItemFile file = new ItemFile(ITEMS_FILE);
        file.open();

        Item item = file.getItemByUserAndName("selluser", "Rolex Watch");
        assertNotNull(item);

        assertEquals(item.getName(), "Rolex Watch");
        assertEquals(item.getSellerUserName(), "selluser");
        assertEquals(item.getBidderUserName(), "");
        assertEquals(item.getDaysToAuction(), 42);
        assertEquals(item.getCurrentBid(), 100.00);
        assertTrue(!item.isNewItem());

        // Close
        file.close();
    }

    public void testAddItem() throws Exception {
        // Create items file
        FileTestHelper.writeLinesToFile(ITEMS_FILE, new String[]{
                "Rolex Watch               selluser                        042 100.00",
                "END"
        });

        // Open
        ItemFile file = new ItemFile(ITEMS_FILE);
        file.open();

        file.addItem(new Item("Potatoes 100kg", "selluser", "", 5, 350.00, true));

        Item item = file.getItemByUserAndName("selluser", "Potatoes 100kg");
        assertNotNull(item);

        assertEquals(item.getName(), "Potatoes 100kg");
        assertEquals(item.getSellerUserName(), "selluser");
        assertEquals(item.getBidderUserName(), "");
        assertEquals(item.getDaysToAuction(), 5);
        assertEquals(item.getCurrentBid(), 350.00);
        assertTrue(item.isNewItem());

        // Close
        file.close();
    }

    public void testRemoveItem() throws Exception {
        // Create items file
        FileTestHelper.writeLinesToFile(ITEMS_FILE, new String[]{
                "Rolex Watch               selluser                        042 100.00",
                "END"
        });

        // Open
        ItemFile file = new ItemFile(ITEMS_FILE);
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