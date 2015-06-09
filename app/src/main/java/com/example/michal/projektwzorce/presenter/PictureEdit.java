package com.example.michal.projektwzorce.presenter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.MenuItem;


import java.util.ArrayList;


import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.controller.Zapis;
import com.example.michal.projektwzorce.model.Photography;

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


        Picture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(PictureEdit.this, Zoom.class);
                startActivity(intent);
            }});


        ImageButton czarnoBialy;
        ImageButton Sepia;
        ImageButton Negatyw;
        ImageButton Przesycenie;

        czarnoBialy = (ImageButton) findViewById(R.id.imageButtonBlackWhite);
        Sepia = (ImageButton) findViewById(R.id.imageButtonSepia);
        Negatyw = (ImageButton) findViewById(R.id.imageButtonFilter3);
        Przesycenie = (ImageButton) findViewById(R.id.imageButtonFilter4);


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
                Intent intent = new Intent(PictureEdit.this, Editable.class);
                startActivity(intent);

                return true;

            case R.id.zapis:

                Zapis.zapis();
                Toast.makeText(getApplicationContext(),"zapisano kurwo", Toast.LENGTH_LONG).show();
                MediaStore.Images.Media.insertImage(getContentResolver(), Photography.getInstance().getPhoto(), "moje foto", "zapis");

                return true;

            case R.id.przyciecie:

                Intent cropIntent = new Intent("com.android.camera.action.CROP");
                startActivity(cropIntent);

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

    public void facebook(){
        share();

    }





    public void share()
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        Uri image = Uri.parse(("android.resource://" + getPackageName() + "/" + Photography.getInstance().getPhoto()));
        sharingIntent.setType("image/png");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, image);
        startActivity(Intent.createChooser(sharingIntent, "Share image using"));


    }
}



