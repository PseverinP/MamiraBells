package com.androidcollider.mamirabells.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidcollider.mamirabells.MainActivity;
import com.androidcollider.mamirabells.R;
import com.androidcollider.mamirabells.Utils;
import com.androidcollider.mamirabells.database.SharedPref;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;

/**
 * Created by s.parkhomenko on 28.05.2015.
 */
public class FrgAdd extends CommonFragment implements OnMapReadyCallback {

    private LatLng placeCoord;
    private int screenWidthDP;

    static FrgAdd newInstance(double bellCoordLat, double bellCoordLng) {
        FrgAdd f = new FrgAdd();

        Bundle args = new Bundle();
        args.putDouble("bellCoordLat", bellCoordLat);
        args.putDouble("bellCoordLng", bellCoordLng);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_add, null);
        screenWidthDP = (int)Utils.getScreenWidthInDP();
        int photoWidth = (int)(screenWidthDP*0.8);
        placeCoord = new LatLng(getArguments().getDouble("bellCoordLat"),getArguments().getDouble("bellCoordLng"));

        ImageView ivPhoto = (ImageView)rootView.findViewById(R.id.frg_add_iv_photo);
        ivPhoto.setMaxWidth(photoWidth);
        //ivPhoto.setMaxHeight(photoWidth);
        ivPhoto.setImageBitmap(getLastPhoto(SharedPref.getInstance().loadLastPhotoPath(),(int)(Utils.getScreenWidthInPX()*0.8)));

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.frg_add_map);
        mapFragment.getMapAsync(this);
        return rootView;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (placeCoord!=null){
            googleMap.addMarker(new MarkerOptions().position(placeCoord));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(placeCoord, 10));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_map_next, menu);
    }

    @Override
    public String getTitle() {
        return getString(R.string.bell_data);
    }

    private Bitmap getLastPhoto (String path, int size){
        File imgFile = new  File(path);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            myBitmap = myBitmap.createScaledBitmap(myBitmap,size,size,false);
            return myBitmap;
        }
        return null;
    }
}
