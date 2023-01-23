package com.app.e_readerfinalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import kotlin.jvm.Synchronized;

public class Navigation2Fragment extends Fragment {
    //Making Singleton Object For class
    private Navigation2Fragment() {
    }

    private static Navigation2Fragment instance = null;

    @Synchronized
    public static Navigation2Fragment getInstance() {
        if (instance == null) instance = new Navigation2Fragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation2, container, false);
    }
}