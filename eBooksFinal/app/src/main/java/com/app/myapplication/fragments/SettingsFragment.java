package com.app.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.app.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import kotlin.jvm.Synchronized;

public class SettingsFragment extends Fragment {
    //Making Singleton Object For class
    public SettingsFragment(){}

    private static SettingsFragment instance = null;

    SwitchCompat scNight;
    @Synchronized
    public static SettingsFragment getInstance() {
        if(instance == null) instance = new SettingsFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_option2, container, false);

        scNight =  v.findViewById(R.id.scNight);
        scNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

            }
        });

        return v;

    }
}