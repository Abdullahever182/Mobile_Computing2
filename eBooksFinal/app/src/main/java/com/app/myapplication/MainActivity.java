package com.app.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.myapplication.fragments.BooksFragment;
import com.app.myapplication.fragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    public ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    private static final int PERMISSION_REQUEST_CODE = 100;
    Button read;
    ArrayList<String> myList;
    ListView listview;
    private TextView tvTotals;
    private ArrayList<String> alPdfSize =  new ArrayList<>();
    private ArrayList<String> alEpubSize =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
        initDrawer();

        checkPermission();

        replaceFragments(BooksFragment.getInstance());

        /*tvTotals =  findViewById(R.id.tvTotals);
        listview = findViewById(R.id.list);
        read = findViewById(R.id.read);
        myList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (Environment.isExternalStorageManager()) {
                //todo when permission is granted
            } else {
                //request for the permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
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

                                                    myList.add(listFile[i].getName());
                                                    ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, myList);
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

            }
        });*/
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

                        myList.add(listFile[i].getName());

                        myList = (ArrayList<String>)myList.stream().distinct().collect(Collectors.toList());

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, myList);
                        listview.setAdapter(arrayAdapter);
                    }
                }
            }
        }
        //return myList;
    }

   /* private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(MainActivity.this,android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }*/

    public  boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("abd","Permission is granted");
                return true;
            } else {

                Log.e("abc","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.e("abc","Permission is granted");
            return true;
        }
    }

   /* private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,  android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to read  files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                    {android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
    }

    private void initDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navView.setNavigationItemSelectedListener(menuItem -> {
            int id=menuItem.getItemId();
            if(id == R.id.option_1){
                replaceFragments(BooksFragment.getInstance());
            }

            else if(id == R.id.option_2){
                replaceFragments(SettingsFragment.getInstance());
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragments(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }
}