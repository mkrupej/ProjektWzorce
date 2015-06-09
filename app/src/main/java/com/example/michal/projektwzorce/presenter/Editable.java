package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.model.Photography;

/**
 * Created by Krzysztof on 2015-06-09.
 */
public class Editable extends Activity {
    ImageView Picture;
    Button Zapisz;
    SeekBar Contrast;
    SeekBar Brightnes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable);
        Picture = (ImageView) findViewById(R.id.Pic);
        Zapisz = (Button) findViewById(R.id.Save);
        Picture.setImageBitmap(Photography.getInstance().getPhoto());


        int brightness;
        SeekBar seekBarBrightness = (SeekBar) findViewById(R.id.jasnosc);
        SeekBar seekBarContrast = (SeekBar) findViewById(R.id.Kontrast);

        seekBarContrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0)
            {
            }
            public void onStartTrackingTouch(SeekBar arg0)
            {
            }
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
            {
                Bitmap newBitMap = doBrightness(Photography.getInstance().getPhoto(), progress);
                Picture.setImageBitmap(newBitMap);
            }
        });


        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0)
            {
            }
            public void onStartTrackingTouch(SeekBar arg0)
            {
            }
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
            {
                Bitmap newBitMap = adjustedContrast(Photography.getInstance().getPhoto(), progress);
                Picture.setImageBitmap(newBitMap);
            }
        });}


    public static Bitmap doBrightness(Bitmap src, int value) {

        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());

        int A, R, G, B;
        int pixel;

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {

                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);


                R += value;
                if (R > 255) {
                    R = 255;
                } else if (R < 0) {
                    R = 0;
                }

                G += value;
                if (G > 255) {
                    G = 255;
                } else if (G < 0) {
                    G = 0;
                }

                B += value;
                if (B > 255) {
                    B = 255;
                } else if (B < 0) {
                    B = 0;
                }

                // apply new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        // return final image
        return bmOut;
   }

    private Bitmap adjustedContrast(Bitmap src, double value)
    {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap

        // create a mutable empty bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());

        // create a canvas so that we can draw the bmOut Bitmap from source bitmap
        Canvas c = new Canvas();
        c.setBitmap(bmOut);

        // draw bitmap to bmOut from src bitmap so we can modify it
        c.drawBitmap(src, 0, 0, new Paint(Color.BLACK));


        // color information
        int A, R, G, B;
        int pixel;
        // get contrast value
        double contrast = Math.pow((100 + value) / 100, 2);

        // scan through all pixels
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                // apply filter contrast for every channel R, G, B
                R = Color.red(pixel);
                R = (int)(((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(R < 0) { R = 0; }
                else if(R > 255) { R = 255; }

                G = Color.green(pixel);
                G = (int)(((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(G < 0) { G = 0; }
                else if(G > 255) { G = 255; }

                B = Color.blue(pixel);
                B = (int)(((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
                if(B < 0) { B = 0; }
                else if(B > 255) { B = 255; }

                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        return bmOut;
    }
}
