package com.pandehoz.viajary.dao;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by test on 14.03.15.
 */
public class Trips {

    public static List<TripItem> ITEMS = new ArrayList<TripItem>();

    private static Context applicationcontext;

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, TripItem> ITEM_MAP = new HashMap<String, TripItem>();

    private static String FILENAME = "trips.json";

    static {
        // Add 3 sample items.
        //addItem(new TripItem(1, "Berlin", "15 Dec 2015", "18 Dec 2015", "100"), "1");
        //addItem(new TripItem(2, "Paris", "15 Dec 2015", "18 Dec 2015", "100"), "1");
        //addItem(new TripItem(3, "Amsterdam", "15 Dec 2015", "18 Dec 2015", "100"), "1");
    }

    public static void setContext(Context context){
        applicationcontext = context.getApplicationContext();
    }

    public static void retrieveTrips(){
        FileInputStream fis = null;
        ITEMS.clear();
        try {
            File file = applicationcontext.getFileStreamPath(FILENAME);
            if(file.exists())
                fis = applicationcontext.openFileInput(FILENAME);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fis != null) {
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            int id = 0;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    try {
                        id++;
                        JSONObject jsonObject = new JSONObject(line);
                        addItem(new TripItem(id, jsonObject.getString("tripTitle"),
                                jsonObject.getString("startDate"),
                                jsonObject.getString("endDate"),
                                "100"));
                        ;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addItem(TripItem item) {
        ITEMS.add(item);
        //ITEM_MAP.put(id, item);
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
