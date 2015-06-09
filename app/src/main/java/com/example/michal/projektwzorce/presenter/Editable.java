package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.controller.SeekBarsProgress;
import com.example.michal.projektwzorce.model.Photography;

/**
 * Created by Krzysztof on 2015-06-09.
 */
public class Editable extends Activity {
    ImageView Picture;
    Button Zapisz;
    Bitmap newBitMap;
    protected void onStop(){
        super.onStop();
        if(Photography.getInstance().getCopy() == null)
            Picture.setImageBitmap(Photography.getInstance().getPhoto());
        else
            Picture.setImageBitmap(Photography.getInstance().getCopy());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable);
        Picture = (ImageView) findViewById(R.id.Pic);
        Zapisz = (Button) findViewById(R.id.Save);

        Picture.setImageBitmap(Photography.getInstance().getCopy());


        SeekBar seekBarBrightness = (SeekBar) findViewById(R.id.jasnosc);
        SeekBar seekBarContrast = (SeekBar) findViewById(R.id.Kontrast);

        Zapisz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Photography.getInstance().setPhoto(newBitMap);
                Intent intent = new Intent(Editable.this, PictureEdit.class);
                startActivity(intent);
            }});

            seekBarContrast.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar arg0)
            {
            }
            public void onStartTrackingTouch(SeekBar arg0)
            {
            }
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
            {
                newBitMap = SeekBarsProgress.doBrightness(Photography.getInstance().getCopy(), progress);
                Picture.setImageBitmap(newBitMap);
                Photography.getInstance().setCopyForSeeks(newBitMap);
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
                if(Photography.getInstance().getCopyForSeeks()==null)
                {
                newBitMap = SeekBarsProgress.adjustedContrast(Photography.getInstance().getCopy(), progress);
                }
                else{newBitMap = SeekBarsProgress.adjustedContrast(Photography.getInstance().getCopyForSeeks(), progress);}
                Picture.setImageBitmap(newBitMap);
            }
        });}
}
