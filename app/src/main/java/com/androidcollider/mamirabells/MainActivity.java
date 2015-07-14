package com.androidcollider.mamirabells;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.androidcollider.mamirabells.fragments.CommonFragment;
import com.androidcollider.mamirabells.fragments.FrgMain;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private int menuId = R.menu.menu_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(new FrgMain());
    }

    private void treatFragment(CommonFragment f, boolean addToBackStack, boolean replace){
        String tag = f.getRealTag();
        FragmentTransaction ft =  getFragmentManager().beginTransaction();
        if(replace){
            ft.replace(R.id.fragment_container, f, tag);
        }else{
            Fragment currentTop = getTopFragment();
            if(currentTop!=null) ft.hide(currentTop);
            ft.add(R.id.fragment_container, f, tag);
        }
        if(addToBackStack) ft.addToBackStack(tag);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onBackStackChanged() {
        int cnt = getFragmentManager().getBackStackEntryCount();
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(cnt > 0);
        }

        Fragment topFragment = getTopFragment();

        if(topFragment instanceof CommonFragment){
            setTitle(((CommonFragment) topFragment).getTitle());
        }
        hideKeyboard();
    }

    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void addFragment(CommonFragment f){
        treatFragment(f, true, false);
    }

    public void replaceFragment(CommonFragment f){
        treatFragment(f,false,true);
    }

    public Fragment getTopFragment(){
        return getFragmentManager().findFragmentById(R.id.fragment_container);
    }
}