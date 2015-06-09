package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
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
        Contrast = (SeekBar) findViewById(R.id.Kontrast);
        Brightnes = (SeekBar) findViewById(R.id.jasnosc);


        int brightness;
        SeekBar seekBarBrightness = (SeekBar) findViewById(R.id.jasnosc);
        seekBarBrightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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
        });}


    public static Bitmap doBrightness(Bitmap src, int value) {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;

        // scan through all pixels
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                // increase/decrease each channel
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
}






