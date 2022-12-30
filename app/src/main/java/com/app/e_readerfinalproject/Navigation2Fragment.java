package com.app.e_readerfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kotlin.jvm.Synchronized;

public class Navigation2Fragment extends Fragment {
    //Making Singleton Object For class
    private Navigation2Fragment() {
    }
EditText name, email;
    Button btnPdf, btnView;
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
    name = (EditText) findViewById(R.id.name);
    email = (EditText) findViewById(R.id.email);
    btnPdf = (Button) findViewById(R.id.btnpdf);
    btnView = (Button) findViewById(R.id.btnview);

    btnPdf.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String username = name.getText().toString();
            String useremail = email.getText().toString();
            String path = getExternalFilesDir(null).toString()+"/user.pdf";
            File file = new File(path);

            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Document document = new Document(PageSize.A4);
            try {
                PdfWriter.getInstance(document, new FileOutputStream(file.getAbsoluteFile()));

            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            document.open();
            Font myfont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD);

            Paragraph paragraph = new Paragraph();
            paragraph.add(new Paragraph("User Name: "+username, myfont));
            paragraph.add(new Paragraph("\n"));
            paragraph.add(new Paragraph("User Email: "+useremail, myfont));

            try {
                document.add(paragraph);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            document.close();

            Toast.makeText(MainActivity.this, "PDF Created Successfully", Toast.LENGTH_SHORT).show();
        }
    });
    btnView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)  {
            Intent intent = new Intent(getApplicationContext(),NewActivity.class);
            startActivity(intent);
        }
    });
    }

}