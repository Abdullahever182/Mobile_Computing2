package com.app.myapplication.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.app.myapplication.MainActivity;
import com.app.myapplication.R;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

import kotlin.jvm.Synchronized;


public class BooksFragment extends Fragment {

    private static final int PERMISSION_REQUEST_CODE = 100;
    Button read;
    private ArrayList<String> myList;
    private ArrayList<String> alNames;
    ListView listview;
    private TextView tvTotals;
    private ArrayList<String> alPdfSize =  new ArrayList<>();
    private ArrayList<String> alEpubSize =  new ArrayList<>();

    public BooksFragment(){}
    private static BooksFragment instance = null;
    @Synchronized
    public static BooksFragment getInstance() {
        if(instance == null) instance = new BooksFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_option1, container, false);


        tvTotals =  v.findViewById(R.id.tvTotals);
        listview = v.findViewById(R.id.list);
        read = v.findViewById(R.id.read);
        myList = new ArrayList<>();
        alNames = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (Environment.isExternalStorageManager()) {
                //todo when permission is granted
            } else {
                //request for the permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state = Environment.getExternalStorageState();

                Log.e("abc", " ===========state======"+state);

                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() );
                    if(dir.exists()) {
                        Log.e("Build.VERSION.SDK_INT > 23", dir.toString());
                        if (dir.isDirectory()) {
                            File listFile[] = dir.listFiles();
                            if (listFile != null && listFile.length > 0) {
                                for (int i = 0; i < listFile.length; i++) {

                                    if (listFile[i].isDirectory()) {
                                        //fileList.add(listFile[i]);
                                        getfile(listFile[i]);

                                    } else {
                                        if (listFile[i].getName().endsWith(".pdf") || listFile[i].getName().endsWith(".epub")) {

                                            if (listFile[i].getName().endsWith(".pdf")) {
                                                alPdfSize.add(listFile[i].getName());
                                            }

                                            if (listFile[i].getName().endsWith(".epub")) {
                                                alEpubSize.add(listFile[i].getName());
                                            }

                                            myList.add(listFile[i].getAbsolutePath());

                                            myList = (ArrayList<String>)myList.stream().distinct().collect(Collectors.toList());

                                          /*  for(int k =0;  i< myList.size(); i++){
                                                File f =  new File(myList.get(i));
                                                alNames.add(f.getName());
                                            }*/

                                            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, myList);
                                            listview.setAdapter(arrayAdapter);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.e("abc","=========  file clicked"+myList.get(i));
                File file =  new File(myList.get(i));

                String fileName  = file.getName();
                if(fileName.endsWith(".epub")){
                    Log.e("abc","========= Epub file clicked"+myList.get(i));

                    //   Log.e("abc","========= Epub file clicked"+alCompletePaths.get(i));
                    Uri path = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", file);
                    //Uri path = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", new File(alCompletePaths.get(i)));

                    Intent target = new Intent(Intent.ACTION_VIEW);
                    target.setDataAndType(path,"application/epub+zip");
                    target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    Intent intent = Intent.createChooser(target, "Open File");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.e("abc","========= PDF file clicked"+myList.get(i));

                    Uri path = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", file);
                    Intent target = new Intent(Intent.ACTION_VIEW);
                    target.setDataAndType(path,"application/pdf");
                    target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    target.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    Intent intent = Intent.createChooser(target, "Open File");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                }


            }
        });

        return v;
    }

    public void getfile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {

                    getfile(listFile[i]);

                } else {
                    if (listFile[i].getName().endsWith(".pdf")||listFile[i].getName().endsWith(".epub"))
                    {
                        if(listFile[i].getName().endsWith(".pdf")){
                            alPdfSize.add(listFile[i].getName());
                        }

                        if(listFile[i].getName().endsWith(".epub")){
                            alEpubSize.add(listFile[i].getName());
                        }

                        alEpubSize = (ArrayList<String>)alEpubSize.stream().distinct().collect(Collectors.toList());
                        alPdfSize = (ArrayList<String>)alPdfSize.stream().distinct().collect(Collectors.toList());
                        tvTotals.setText("Total PDF=" + alPdfSize.size() +",  Total ePUB="+alEpubSize.size());

                        myList.add(listFile[i].getAbsolutePath());
                        myList = (ArrayList<String>)myList.stream().distinct().collect(Collectors.toList());

/*

                        for(int k =0;  i< myList.size(); i++){
                            File f =  new File(myList.get(i));
                            alNames.add(f.getName());
                        }
*/

                        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, myList);
                        listview.setAdapter(arrayAdapter);
                    }
                }
            }
        }
        //return myList;
    }
}