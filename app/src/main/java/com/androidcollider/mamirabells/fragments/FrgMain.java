package com.androidcollider.mamirabells.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.androidcollider.mamirabells.App;
import com.androidcollider.mamirabells.R;
import com.androidcollider.mamirabells.database.SharedPref;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.lang.reflect.Type;

/**
 * Created by s.parkhomenko on 28.05.2015.
 */
public class FrgMain extends CommonFragment{


    private int menuId = R.menu.menu_main;
    private final int CAMERA_RESULT = 0;
    private Uri outputFileUri;
    private SharedPref sPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sPref = new SharedPref(getActivity());



        return inflater.inflate(R.layout.frg_main, null);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(menuId, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_add){
            addNewBell();
        }
        return true;
    }

    private void addNewBell(){
        makeBellPhoto();
    }

    private void makeBellPhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        long photoIndex = sPref.loadLastPhotoIndex()+1;
        String path = Environment.getExternalStorageDirectory().getPath()+
                "/MamiraBells/bell_"+photoIndex+".jpg";
        File file = new File(path);
        sPref.saveLastPhotoPath(path);
        outputFileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, CAMERA_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_RESULT) {
            addFragment(new FrgMap());
        }
    }

    @Override
    public String getTitle() {
        return getString(R.string.app_name);
    }
}
