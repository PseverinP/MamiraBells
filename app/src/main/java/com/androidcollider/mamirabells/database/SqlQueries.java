package com.androidcollider.mamirabells.database;

/**
 * Created by pseverin on 22.12.14.
 */
public class SqlQueries {
    //make a string SQL request for Bell table
    public static final String create_bell_table = "CREATE TABLE Bell (" +
            "id               INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "date             INTEGER NOT NULL," +
            "name             TEXT NOT NULL," +
            "description      TEXT NOT NULL," +
            "presenter        TEXT NOT NULL," +
            "photo_path       TEXT NOT NULL," +
            "country          TEXT NOT NULL," +
            "location         TEXT NOT NULL," +
            "location_lat     REAL NOT NULL," +
            "location_lng     REAL NOT NULL" +
            ");";

}
