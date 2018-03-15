package com.scarz.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Available items file used to get currently available items for auction
 */
public class ItemFile extends File {
    private List<Item> mItems;

    /**
     * Initializes item file with file path
     * @param name File path
     */
    public ItemFile(String name) {
        super(name);

        mItems = new ArrayList<>();
    }

    /**
     * Opens file and reads the items to memory
     * @throws IOException Thrown when an error occurs while reading the file
     */
    public void open() throws IOException {
        // TODO: Open file in read mode, read all items, close handle
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Writes the updated item list to the file and closes the stream
     * @throws IOException Thrown when an error occurs while writing the file
     */
    @Override
    public void close() throws IOException {
        // TODO: Open file in write mode, write all lines, close handle
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Gets all of the items that are currently available in the system
     * @return All of the items that are present within the item file
     */
    public List<Item> getItems() {
        return mItems;
    }

    /**
     * Attempts to find an item by the specified name and creator
     * @param seller User which created the item
     * @param name Item name to search for
     * @return Pointer to item found, if any
     */
    public Item getItemByUserAndName(String seller, String name) {
        // TODO: Find item by user, and name
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Adds the item to the list of available items
     * @param item Item to add
     */
    public void addItem(Item item) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Removes the item from the list of available items
     * @param item Item to remove
     */
    public void removeItem(Item item) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
