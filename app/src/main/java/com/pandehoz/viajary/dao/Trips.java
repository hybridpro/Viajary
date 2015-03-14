package com.pandehoz.viajary.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by test on 14.03.15.
 */
public class Trips {

    public static List<TripItem> ITEMS = new ArrayList<TripItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, TripItem> ITEM_MAP = new HashMap<String, TripItem>();

    static {
        // Add 3 sample items.
        //addItem(new TripItem(1, "Berlin", "15 Dec 2015", "18 Dec 2015", "100"), "1");
        //addItem(new TripItem(2, "Paris", "15 Dec 2015", "18 Dec 2015", "100"), "1");
        //addItem(new TripItem(3, "Amsterdam", "15 Dec 2015", "18 Dec 2015", "100"), "1");
    }

    private static void addItem(TripItem item, String id) {
        ITEMS.add(item);
        ITEM_MAP.put(id, item);
    }

    public static class TripItem {
        public int id;
        public String tripTitle;
        public String startDate;
        public String endDate;
        public String expenses;

        public TripItem(int id, String tripTitle, String startDate, String endDate, String expenses) {
            this.tripTitle = tripTitle;
            this.startDate = startDate;
            this.endDate = endDate;
            this.expenses = expenses;
            this.id = id;
        }
    }
}
