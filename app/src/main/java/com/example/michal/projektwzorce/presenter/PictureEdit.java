package com.example.michal.projektwzorce.presenter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;



import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.model.Photography;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
public class PictureEdit extends Activity  {

    // private static Photography photo =  Photography.getInstance();

    ImageView Picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_edit);
        getActionBar().setDisplayShowTitleEnabled(false);
        Picture = (ImageView) findViewById(R.id.Pic);
        Picture.setImageBitmap(Photography.getInstance().getPhoto());


        ImageButton czarnoBialy;
        ImageButton Sepia;
        ImageButton Negatyw;
        ImageButton Przesycenie;

        czarnoBialy = (ImageButton) findViewById(R.id.imageButtonBlackWhite);
        Sepia = (ImageButton) findViewById(R.id.imageButtonSepia);
        Negatyw = (ImageButton) findViewById(R.id.imageButtonFilter3);
        Przesycenie = (ImageButton) findViewById(R.id.imageButtonFilter4);


        ActionBar bar = getActionBar();
        ArrayList<String> list = new ArrayList<String>();
        list.add("Kontrast");
        list.add("Jasnoœæ");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_picture_edit, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.seekbar1:

                settings();

                return true;

            case R.id.zapis:

                zapis();

                return true;

            case R.id.przyciecie:

                settings();

                return true;

            case R.id.home:

                home();

                return true;

            case R.id.facebook:

                facebook();

                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }

    public void settings() {
        Toast.makeText(this, "Home Option Selexted", Toast.LENGTH_SHORT).show();
    }

    public void home() {
        Intent back = new Intent(PictureEdit.this, ChoiceActivity.class);
        PictureEdit.this.startActivity(back);
    }

    public void facebook() {

    }

    public void zapis() {

        BitmapDrawable drawable2 = (BitmapDrawable) Picture.getDrawable();
        Bitmap bitmap2 = drawable2.getBitmap();
        //Funkcje.ostateczna= bitmap2;
        String extStorageDirectory = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM).toString();
        OutputStream outStream = null;
        File file = new File(extStorageDirectory + "/DCIM");
        try {
            outStream = new FileOutputStream(file);
            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
        }
    }




    public void share(String nazwa) {

    }
}



