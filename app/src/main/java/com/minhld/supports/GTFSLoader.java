package com.minhld.supports;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;

import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by minhld on 11/13/2015.
 */
public class GTFSLoader {
    private static final String ROUTE_PATH = "/android_asset/routes.txt";
    private static final String AGENCY_PATH = "/android_asset/agency.txt";

    public static ArrayList<Route> routes = new ArrayList<>();

    public static void loadGTFS(Context c) throws IOException {
        getRoutes(c);
    }

    private static void getRoutes(Context c) throws IOException {
        InputStream routeStream = c.getAssets().open("routes.txt", Context.MODE_WORLD_READABLE);
        List<Map<String, String>> lines = readCsv(routeStream);
        for (Map<String, String> line : lines) {
            GTFSLoader.routes.add(new Route(line.get("route_id"), line.get("route_short_name"),
                            line.get("route_long_name"), Integer.parseInt(line.get("route_type"))));
        }
    }

    private static List<Map<String, String>> readCsv(InputStream csv) throws IOException {
        CSVReader reader = new CSVReader(new InputStreamReader(csv));
        ArrayList<Map<String, String>> lines = new ArrayList<Map<String,String>>();
        String[] columnNames = reader.readNext();
        if (columnNames == null) {
            return lines;
        }
        String[] line;
        while((line = reader.readNext()) != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            for (int ci = 0; ci < line.length; ++ci) {
                map.put(columnNames[ci], line[ci]);
            }
            lines.add(map);
        }
        return lines;
    }
}
