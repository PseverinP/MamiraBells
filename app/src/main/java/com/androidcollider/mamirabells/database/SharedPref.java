package com.androidcollider.mamirabells.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.androidcollider.mamirabells.App;

/**
 * Created by pseverin on 21.01.15.
 */
public class SharedPref {

    private Context context;
    private SharedPreferences sharedPreferences;
    private final static String APP_PREFERENCES = "MamiraBellsPref";

    private static volatile SharedPref instance = null;

    public static SharedPref getInstance(){
        if(instance == null) {
            instance = new SharedPref(App.getContext());
        }
        return instance;
    }

    public SharedPref(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void saveLastPhotoPath(String photoPath){
        sharedPreferences.edit().putString("photoPath", photoPath).apply();
    }

    public String loadLastPhotoPath(){
        return sharedPreferences.getString("photoPath","");
    }

    public void saveLastPhotoIndex(long index){
        sharedPreferences.edit().putLong("photoIndex", index).apply();
    }

    public long loadLastPhotoIndex(){
        return sharedPreferences.getLong("photoIndex", 0);
    }

}
