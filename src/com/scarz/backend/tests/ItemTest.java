package com.scarz.backend.tests;

import com.scarz.backend.Item;
import junit.framework.TestCase;

public class ItemTest extends TestCase {
    public void testGetName() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.getName(), "Potatoes 100kg");
    }

    public void testGetSellerUserName() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.getSellerUserName(), "selluser");
    }

    public void testGetBidderUserName() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.getBidderUserName(), "buyuser");
    }

    public void testGetDaysToAuction() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.getDaysToAuction(), 3);
    }

    public void testSetDaysToAuction() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.getDaysToAuction(), 3);
        item.setDaysToAuction(5);
        assertEquals(item.getDaysToAuction(), 5);
    }

    public void testGetCurrentBid() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.getCurrentBid(), 300.00);
    }

    public void testSetBidderUserName() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.getBidderUserName(), "buyuser");
        item.setBidderUserName("fulluser");
        assertEquals(item.getBidderUserName(), "fulluser");
    }

    public void testSetCurrentBid() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.getCurrentBid(), 300.00);
        item.setCurrentBid(350.00);
        assertEquals(item.getCurrentBid(), 350.00);
    }

    public void testIsNewItem() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(item.isNewItem(), true);
    }
}