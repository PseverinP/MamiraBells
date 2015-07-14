package com.androidcollider.mamirabells.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by s.parkhomenko on 28.05.2015.
 */
public class Bell {
    private int id;
    private Date date;
    private String name;
    private String description;
    private String presenter;
    private String photoPath;
    private String country;
    private String location;
    private LatLng coordinates;

    public Bell(Date date, String name, String description, String presenter, String photoPath, String country, String location, LatLng coordinates) {
        this.date = date;
        this.name = name;
        this.description = description;
        this.presenter = presenter;
        this.photoPath = photoPath;
        this.country = country;
        this.location = location;
        this.coordinates = coordinates;
    }

    public Bell(int id, Date date, String name, String description, String presenter, String photoPath, String country, String location, LatLng coordinates) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this.presenter = presenter;
        this.photoPath = photoPath;
        this.country = country;
        this.location = location;
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPresenter() {
        return presenter;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }
}
