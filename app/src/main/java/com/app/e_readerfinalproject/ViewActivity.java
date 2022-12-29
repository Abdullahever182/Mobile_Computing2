package com.app.e_readerfinalproject;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class ViewActivity extends AppCompatActivity {

    PDFView pdfView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

//        pdfView = (PDFView) findViewById(R.id.pdf_viewer);
//        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
//
//        if (getIntent() != null) {
//            String viewType = getIntent().getStringExtra("ViewType");
//            if (viewType != null || !TextUtils.isEmpty(viewType)) {
//                if (viewType.equals("assets"))
//                {
//                    pdfView.fromAsset("pdf_example.pdf")
//                            .password(null)  // If have password
//                            .defaultPage(0)  //Open default page, you can remember this value to open from last time
//                            .enableSwipe(true)
//                            .swipeHorizontal(false)
//                            .enableDoubletap(true) //Double tap to zoom
//                            .onDraw(new onDrawListner()  {
//                                @Override
//                                public void onLayerDrawn(Canvas canvas, float pageWidth, float PageHeight, int displayedPage) {
//                                    // Code here if you want to do something
//                                }
//
//                }
//                    .onDrawAll(new onDrawListner() {
//                    @Override
//                    public void onLayerDrawn(Canvas canvas, float pageWidth, float PageHeight, int displayedPage) {
//                        // Code here if you want to do something
//                    }
//                    .onPageError(new onPageErrorListner() {
//                        @Override
//                        public void onPageError(int page, Throwable t) {
//                            Toast.makeText(viewActivity.this, "Error while open page"+page, Toast.LENGTH_SHORT.show();
//                            }
//                            .onPageChange (new onPageChangeListner() {
//                        @Override
//                        public void onPageChanged(int page, int pageCount) {
//                            //Code here if you want to do something
//
//                        }
//                    })
//                    .onTap (new newOnTapListner(){
//                        @Override
//                        public boolean onTap(MotionEvent e){
//                            return true;
//                        }
//                    }) .onRender (new onRenderListner() {
//                        @Override
//                                public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight)
//                        {
//                            pdfView.fitToWidth(); //Fixed screen size
//                        }
//                    })
//                            .enableAnnotationRendering(true)
//                    .invalidPageColor(Color.WHITE)
//                    .load();
//                        }
//                            else if(viewType.equals("storage"))
//                        {
//                         Uri pdfFile = Uri.parse(getIntent().getStringExtra("FileUri"));
//
//                            pdfView.fromUri(pdfFile)
//                                    .password(null)  // If have password
//                                    .defaultPage(0)  //Open default page, you can remember this value to open from last time
//                                    .enableSwipe(true)
//                                    .swipeHorizontal(false)
//                                    .enableDoubletap(true) //Double tap to zoom
//                                    .onDraw(new onDrawListner()  {
//                                        @Override
//                                        public void onLayerDrawn(Canvas canvas, float pageWidth, float PageHeight, int displayedPage) {
//                                            // Code here if you want to do something
//                                        }
//
//                                    }
//                                            .onDrawAll(new onDrawListner() {
//                                                @Override
//                                                public void onLayerDrawn(Canvas canvas, float pageWidth, float PageHeight, int displayedPage) {
//                                                    // Code here if you want to do something
//                                                }
//                    .onPageError(new onPageErrorListner() {
//                                                    @Override
//                                                    public void onPageError(int page, Throwable t) {
//                                                        Toast.makeText(viewActivity.this, "Error while open page"+page, Toast.LENGTH_SHORT.show();
//                                                    }
//                            .onPageChange (new onPageChangeListner() {
//                                                        @Override
//                                                        public void onPageChanged(int page, int pageCount) {
//                                                            //Code here if you want to do something
//
//                                                        }
//                                                    })
//                                                            .onTap (new newOnTapListner(){
//                                                                @Override
//                                                                public boolean onTap(MotionEvent e){
//                                                                    return true;
//                                                                }
//                                                            }) .onRender (new onRenderListner() {
//                                                                @Override
//                                                                public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight)
//                                                                {
//                                                                    pdfView.fitToWidth(); //Fixed screen size
//                                                                }
//                                                            })
//                                                            .enableAnnotationRendering(true)
//                                                            .invalidPageColor(Color.WHITE)
//                                                            .load();
//                        }
//
//                    }
//                }
//            }
//
//        }
//    }
}}