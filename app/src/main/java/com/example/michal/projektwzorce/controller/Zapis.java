package com.example.michal.projektwzorce.controller;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.michal.projektwzorce.model.Photography;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Krzysztof on 2015-05-19.
 */
public final class Zapis
{
    public static void zapis()
    {
        Bitmap bitmap2 = Photography.getInstance().getPhoto();

     /*   FileOutputStream out = null;
        try {
            out = new FileOutputStream("nazwa");
            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } */


    }


}
