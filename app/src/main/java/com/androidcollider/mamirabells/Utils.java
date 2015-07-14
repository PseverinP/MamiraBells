package com.androidcollider.mamirabells;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by s.parkhomenko on 04.06.2015.
 */
public class Utils {
    public static void DBG(String s){
        Log.d("MamiraBells DEBUGER", s);
    }
    public static void DBG(String s1, String s2){
        DBG(s1 + ": " + s2);
    }


    public static int getScreenWidthInPX(){
        WindowManager wm = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();

        display.getSize(size);

        return  size.x;
    }

    public static float getScreenWidthInDP(){
        WindowManager wm = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = App.getContext().getResources().getDisplayMetrics().density;
        float dpWidth  = outMetrics.widthPixels / density;

        return dpWidth;
    }
}
