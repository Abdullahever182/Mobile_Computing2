package com.app.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;

    private void performStorageOperations() {
        File dir = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Download");
        if (!dir.exists() && !dir.mkdirs()) {
            Log.e("MyApp", "Error creating directory");
        }
        if (dir.exists()) {
            Log.d("path", dir.toString());
            File[] list = dir.listFiles();
            for (int i = 0; i < list.length; i++) {
                myList.add(list[i].getName());
                if (list[i].getName().endsWith(".pdf")) {
                    File pdfFile = new File(getFilesDir() + "/Download/" + list[i].getName());
                    if (pdfFile.exists()) {
                        Uri path = Uri.fromFile(pdfFile);
                        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                        pdfIntent.setDataAndType(path, "application/pdf");
                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(pdfIntent);
                    }
                } else if (list[i].getName().endsWith(".epub")) {
                    File epubFile = new File(getFilesDir() + "/Download/" + list[i].getName());
                    if (epubFile.exists()) {
                        Uri path = Uri.fromFile(epubFile);
                        Intent epubIntent = new Intent(Intent.ACTION_VIEW);
                        epubIntent.setDataAndType(path, "application/epub+zip");
                        epubIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(epubIntent);
                    }
                }
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, myList);
            listview.setAdapter(arrayAdapter);
        }
    }



    public static String getAbsolutePath(Context context, Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            String s = cursor.getString(column_index);
            cursor.close();
            return s;
        } else
            return null;
    }

    Button read;
    ArrayList<String> myList;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.list);
        read = findViewById(R.id.read);
        myList = new ArrayList<>();
        read.setOnClickListener(v -> {
            File dir = new File(getFilesDir(), "Download");
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
                performStorageOperations();
            }
            if (dir.exists()) {
                Log.d("path", dir.toString());
                File[] list = dir.listFiles();
                for (int i = 0; i < list.length; i++) {
                    myList.add(list[i].getName());
                    if (list[i].getName().endsWith(".pdf")) {
                        File pdfFile = new File(getFilesDir() + "/Download/" + list[i].getName());
                        if (pdfFile.exists()) {
                            Uri path = Uri.fromFile(pdfFile);
                            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                            pdfIntent.setDataAndType(path, "application/pdf");
                            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(pdfIntent);
                        }
                    } else if (list[i].getName().endsWith(".epub")) {
                        File epubFile = new File(getFilesDir() + "/Download/" + list[i].getName());
                        if (epubFile.exists()) {
                            Uri path = Uri.fromFile(epubFile);
                            Intent epubIntent = new Intent(Intent.ACTION_VIEW);
                            epubIntent.setDataAndType(path, "application/epub+zip");
                            epubIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(epubIntent);
                        }
                    }
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, myList);
                listview.setAdapter(arrayAdapter);
            }

        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                performStorageOperations();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }else{
                performStorageOperations();
            }

        }
    }

}
