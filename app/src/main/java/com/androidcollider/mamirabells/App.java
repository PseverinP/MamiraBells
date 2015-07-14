package com.androidcollider.mamirabells;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by s.parkhomenko on 26.05.2015.
 */
public class App extends Application {

    private static App instance;
    public static GoogleAnalytics analytics;
    public final static String TRACKER_ID ="UA-58437769-3";


    public static Tracker tracker;

    public App(){
        instance = this;
    }

    public static Context getContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        tracker = analytics.newTracker(TRACKER_ID);
        tracker.enableAutoActivityTracking(true);
    }
}
