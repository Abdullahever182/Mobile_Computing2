package com.app.e_readerfinalproject;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.commons.io.FileUtils;
import pdf.converter.epub.EpubCreator;
import pdf.converter.img.ImageFileExtension;
import pdf.converter.img.ImgCreator;
import pdf.converter.zip.ZipCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import androidx.fragment.app.Fragment;

import kotlin.jvm.Synchronized;

public class Navigation2Fragment extends Fragment {
    //Making Singleton Object For class
    private Navigation2Fragment(){}
    private static Navigation2Fragment instance = null;
    @Synchronized
    public static Navigation2Fragment getInstance() {
        if(instance == null) instance = new Navigation2Fragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation2, container, false);

    }
    public class PdfConverter {
        private static final int RESOLUTION = 300;
        private static final int WIDTH = 600;
        private static final int HEIGHT = 800;
        private File pdf;

        private PdfConverter(File pdf){
            this.pdf = pdf;
        }

        public static PdfConverter convert(File pdf){
            return new PdfConverter(pdf);
        }

        public void intoImage(File output, int resolution, int width, int height, ImageFileExtension format){
            ImgCreator creator = new ImgCreator();
            creator.process(pdf, output, resolution, width, height, format);
        }

        public void intoEpub(String title, File output){
            try {
                //File imgsdir = File.createTempFile(UUID.randomUUID().toString(), "");
                Path imgsdir = new File(String.format("/tmp/%s", UUID.randomUUID().toString())).toPath();
                Files.createDirectories(imgsdir);


                ImgCreator creator = new ImgCreator();
                creator.process(pdf, imgsdir.toFile(), RESOLUTION, WIDTH, HEIGHT, ImageFileExtension.PNG);
                EpubCreator epubCreator = new EpubCreator();
                epubCreator.create(title, imgsdir.toFile(), output);
                FileUtils.deleteDirectory(imgsdir.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void intoEpub(File imgsdir, String title, File output){
            EpubCreator epubCreator = new EpubCreator();
            try {
                epubCreator.create(title, imgsdir, output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void intoZip(String title, File output){
            try {
                File imgsdir = File.createTempFile(UUID.randomUUID().toString(), "");
                imgsdir.mkdirs();
                ImgCreator creator = new ImgCreator();
                creator.process(pdf, imgsdir, RESOLUTION, WIDTH, HEIGHT, ImageFileExtension.PNG);
                ZipCreator zipCreator = new ZipCreator();
                zipCreator.create(imgsdir, output);
                FileUtils.deleteDirectory(imgsdir);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void intoZip(File imgsdir, String title, File output){
            ZipCreator zipCreator = new ZipCreator();
            try {
                zipCreator.create(imgsdir, output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
