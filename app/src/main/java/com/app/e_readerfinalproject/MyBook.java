package com.app.e_readerfinalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import kotlin.jvm.Synchronized;

public class MyBook extends Fragment {


    //Making Singleton Object For class
    private MyBook(){}
    private static MyBook instance = null;
    @Synchronized
    public static MyBook getInstance() {
        if(instance == null) instance = new MyBook();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation1, container, false);

    }
}


