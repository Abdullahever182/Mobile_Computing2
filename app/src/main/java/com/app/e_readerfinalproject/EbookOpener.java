package com.app.e_readerfinalproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class EbookOpener extends AppCompatActivity {

    PDFView myPDFViewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_opener);

//        myPDFViewer = (PDFView) findViewById(R.id.pdfViewer);
//        String getItem = getIntent().getStringExtra("pdfFileName");
//        if (getItem.equals("Bash Beginners Guide")) {
//
//            myPDFViewer.fromAsset("bash_beginners_guide.pdf").load();
//        }

    }
}