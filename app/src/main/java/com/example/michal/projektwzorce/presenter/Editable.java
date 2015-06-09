package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.michal.projektwzorce.R;

/**
 * Created by Krzysztof on 2015-06-09.
 */
public class Editable extends Activity
{
    ImageView Picture;
    Button Zapisz;
    SeekBar Contrast;
    SeekBar Brightnes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable);
        Picture = (ImageView) findViewById(R.id.Pic);
        Zapisz = (Button) findViewById(R.id.Save);
        Contrast = (SeekBar) findViewById(R.id.Kontrast);
        Brightnes = (SeekBar) findViewById(R.id.jasnosc);


        float curBrightnessValue = 0;
        try {
            curBrightnessValue = android.provider.Settings.System.getInt(
                    getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        int screen_brightness = (int) curBrightnessValue;
        Brightnes.setProgress(screen_brightness);
        Brightnes.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue,
                                          boolean fromUser) {
                progress = progresValue;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do something here,
                // if you want to do anything at the start of
                // touching the seekbar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                android.provider.Settings.System.putInt(getContentResolver(),
                        android.provider.Settings.System.SCREEN_BRIGHTNESS,
                        progress);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_editable, menu);
        return true;
    }

}

