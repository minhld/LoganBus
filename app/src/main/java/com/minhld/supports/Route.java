package com.minhld.supports;

/**
 * Created by minhld on 11/13/2015.
 */
public class Route {
    public String id;
    public String shortName;
    public String longName;
    public int type;

    public Route(String id, String shortName, String longName, int type) {
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.type = type;
    }
}
