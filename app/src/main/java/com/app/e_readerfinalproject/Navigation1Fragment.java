package com.app.e_readerfinalproject;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.e_readerfinalproject.adapters.BookAdapters;
import com.app.e_readerfinalproject.models.Bookmodels;

import java.io.File;
import java.util.ArrayList;

import kotlin.jvm.Synchronized;


public class Navigation1Fragment extends Fragment{


    //Making Singleton Object For class
    private Navigation1Fragment(){}
    private static Navigation1Fragment instance = null;
    @Synchronized
    public static Navigation1Fragment getInstance() {
        if(instance == null) instance = new Navigation1Fragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation1, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewBookreader);

        ArrayList<Bookmodels> list = new ArrayList<>();

        list.add(new Bookmodels(1, "HTML tutorial"));
        list.add(new Bookmodels(2, "js tutorial"));
        list.add(new Bookmodels(3, "css tutorial"));
        list.add(new Bookmodels(4, "web development tutorial"));
        list.add(new Bookmodels(5, "java tutorial"));
        list.add(new Bookmodels(6, "php tutorial"));
        list.add(new Bookmodels(7, "React tutorial"));
        list.add(new Bookmodels(8, "app development"));
        list.add(new Bookmodels(9, "Graphic design"));
        list.add(new Bookmodels(10, "Software Engineer"));


        BookAdapters adapters = new BookAdapters(list, getActivity());
        recyclerView.setAdapter(adapters);
//
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        Search_Dir(Environment.getExternalStorageDirectory());
    }
    public void Search_Dir(File dir) {
        String pdfPattern = ".pdf";

        File FileList[] = dir.listFiles();

        if (FileList != null) {
            for (int i = 0; i < FileList.length; i++) {

                if (FileList[i].isDirectory()) {
                    Search_Dir(FileList[i]);
                } else {
                    if (FileList[i].getName().endsWith(pdfPattern)){
                        //here you have that file.
                        Log.i("files","file goes here "+FileList[i].getName());

                    }
                }
            }
        }
    }
}