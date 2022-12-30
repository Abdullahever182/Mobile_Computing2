package com.app.e_readerfinalproject;

import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv_pdf;
    public static ArrayList<File> fileList = new ArrayList<File>();
    PDFAdapter obj_adapter;
    public static int REQUEST_PERMISSIONS = 1;
    boolean boolean_permission;
    File dir;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    private static final int PICK_PDF_CODE = 1000;
    Button btn_open_assets,btn_open_storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Request Read & Write External Storage
//        Dexter.withActivity(this)
//                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                .withListner(new BaseMultiplePermissionsListner(){
//                    @Override
//                    public void onPermissionsChecked(MultiplePermissionsReport report) {
//                        super.onPermissionsChecked(report);
//                    }
//                    @Override
//                    public void onPermissionsRationalShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        super.onPermissionsRationalShouldBeShown(permissions, token);
//                    }
//
//                }).check();
//
//        btn_open_assets = (Button)findViewById(R.id.btn_open_assets);
//        btn_open_storage = (Button)findViewById(R.id.btn_open_assets);
//
//        btn_open_assets.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
//                intent.putExtra("ViewType", "assets");
//                startActivity(intent);
//            }
//        });

//        btn_open_storage.setOnClickListener() {
//            @Override
//                    public void onClick(View view) {
//                Intent browserPDF = new Intent(Intent.ACTION_GET_CONTENT);
//                browserPDF.setType("application/pdf");
//                browserPDF.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(Intent.createChooser(browserPDF, "Select PDF"),PICK_PDF_CODE));
//            }
//        };

        replaceFragments(Navigation1Fragment.getInstance());
        initViews();
        initDrawer();
        init();
    }

//Ctrl+O

//    @Override
//    protected void onActivityResult (int resultCode, @Nullable Intent date) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null){
//            Uri selectedPDF = data.getData();
//            Intent intent = new Intent(MainActivity.this,ViewActivity.class);
//            intent.putExtra("ViewType", "storage");
//            intent.putExtra("FileUri", selectedPDF.toString());
//            startActivity(intent);
//        }
//    }

    private void init() {
        lv_pdf = (ListView) findViewById(R.id.lv_pdf);
        dir = new File(Environment.getExternalStorageDirectory().toString());
        // dir = new File(String.valueOf(Environment.getExternalStorageDirectory()));
//        fn_permission();
    }

//        lv_pdf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            Intent intent = new Intent(getApplicationContext(), EbookOpener.class);
//            intent.putExtra("position", i);
//            startActivity(intent);
//
//
//        }
//        public ArrayList<File> getfile(File dir) {
//            File listFile[] = dir.listFiles();
//            if (listFile != null && listFile.length > 0) {
//                for (int i = 0; i < listFile.length; i++) {
//
//                    if (listFile[i].isDirectory()) {
//                        getfile(listFile[i]);
//
//                    } else {
//
//                        boolean booleanpdf = false;
//                        if (listFile[i].getName().endsWith(".pdf")) {
//
//                            for (int j = 0; j < fileList.size(); j++) {
//                                if (fileList.get(j).getName().equals(listFile[i].getName())) {
//                                    booleanpdf = true;
//                                } else {
//
//                                }
//                            }
//
//                            if (booleanpdf) {
//                                booleanpdf = false;
//                            } else {
//                                fileList.add(listFile[i]);
//
//                            }
//                        }
//                    }
//                }
//            }
//            return fileList;
//        }
//        private void fn_permission() {
//            if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//
//                if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))) {
//                } else {
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            REQUEST_PERMISSIONS);
//
//                }
//            } else {
//                boolean_permission = true;
//
//                getfile(dir);
//
//                obj_adapter = new PDFAdapter(getApplicationContext(), fileList);
//                lv_pdf.setAdapter(obj_adapter);
//
//            }
//        }
//
//        @Override
//        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//            if (requestCode == REQUEST_PERMISSIONS) {
//
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    boolean_permission = true;
//                    getfile(dir);
//
//                    obj_adapter = new PDFAdapter(getApplicationContext(), fileList);
//                    lv_pdf.setAdapter(obj_adapter);
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();
//
//                }
//            }
//        }
//}

//        RecyclerView recyclerView = findViewById(R.id.recyclerviewBookreader);


//        getSupportActionBar().setTitle("MainActivity");


        



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
            int id = menuItem.getItemId();
            if (id == R.id.navigation_1)
                replaceFragments(Navigation1Fragment.getInstance());
            else if (id == R.id.option_2)
                replaceFragments(Navigation2Fragment.getInstance());
            else if (id == R.id.option_3)
                replaceFragments(Navigation3Fragment.getInstance());
            else if (id == R.id.option_4)
                replaceFragments(Navigation4Fragment.getInstance());

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

    }

    private void replaceFragments(Navigation3Fragment instance) {
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