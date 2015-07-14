package com.androidcollider.mamirabells.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.androidcollider.mamirabells.models.Bell;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by pseverin on 22.12.14.
 */
public class DataSource {
    private final static String TAG = "Андроідний Коллайдер";
    private SQLiteDatabase dbLocal;
    private DbHelper dbHelper;
    private Context context;

    public DataSource(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    //Open database
    public void openLocal() throws SQLException {
        if (dbLocal == null || !dbLocal.isOpen()) {
            dbLocal = dbHelper.getWritableDatabase();
        }
    }

    //Close database
    public void closeLocal() {
        if (dbLocal != null && dbLocal.isOpen()) {
            dbLocal.close();
        }
    }

    public void saveBell(Bell bell){
        SharedPref sharedPref = new SharedPref(context);
        openLocal();
        ContentValues cv = new ContentValues();
        cv.put("date", System.currentTimeMillis());
        cv.put("name", bell.getName());
        cv.put("description", bell.getDescription());
        cv.put("presenter", bell.getPresenter());
        cv.put("photo_path", sharedPref.loadLastPhotoPath());
        cv.put("country",bell.getCountry());
        cv.put("location",bell.getLocation());
        cv.put("location_lat",bell.getCoordinates().latitude);
        cv.put("location_lng",bell.getCoordinates().longitude);

        dbLocal.insert("Bell", null, cv);
        closeLocal();
    }

    public ArrayList<Bell> getAllBells() {
        openLocal();
        ArrayList<Bell> bells = new ArrayList<>();
        Cursor cursor = dbLocal.rawQuery("SELECT * FROM Bell",null);


        if (cursor.moveToFirst()) {
            int idColIndex = cursor.getColumnIndex("id");
            int dateColIndex = cursor.getColumnIndex("date");
            int nameColIndex = cursor.getColumnIndex("name");
            int descriptionColIndex = cursor.getColumnIndex("description");
            int presenterColIndex = cursor.getColumnIndex("presenter");
            int photoPathColIndex = cursor.getColumnIndex("photo_path");
            int countryColIndex = cursor.getColumnIndex("country");
            int locationColIndex = cursor.getColumnIndex("location");
            int latColIndex = cursor.getColumnIndex("location_lat");
            int lngColIndex = cursor.getColumnIndex("location_lng");
            do {
                int id = cursor.getInt(idColIndex);
                Date date = new Date(cursor.getLong(dateColIndex));
                String name = cursor.getString(nameColIndex);
                String description = cursor.getString(descriptionColIndex);
                String presenter = cursor.getString(presenterColIndex);
                String photoPath = cursor.getString(photoPathColIndex);
                String country = cursor.getString(countryColIndex);
                String location = cursor.getString(locationColIndex);
                LatLng coordinates = new LatLng(cursor.getDouble(latColIndex),cursor.getDouble(lngColIndex));
                bells.add(new Bell(id,date,name,description,presenter,photoPath,country,location,coordinates));
            }while (cursor.moveToNext());
        }
        cursor.close();
        closeLocal();
        return bells;
    }
}
