package com.example.michal.projektwzorce.presenter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.controller.Crop;
import com.example.michal.projektwzorce.controller.Zapis;
import com.example.michal.projektwzorce.model.Photography;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
public class PictureEdit extends Activity  {

    // private static Photography photo =  Photography.getInstance();

    ImageView Picture;
    public final int PICK_CROP = 10;

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


        ActionBar bar = getActionBar();
        ArrayList<String> list = new ArrayList<String>();
        list.add("Kontrast");
        list.add("Jasno��");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_CROP) {
            if (data != null) {
                // get the returned data
                Bundle extras = data.getExtras();
                // get the cropped bitmap
                Bitmap selectedBitmap = extras.getParcelable("data");
                startActivityForResult(Crop.przytnij(data.getData()), PICK_CROP);
                Picture.setImageBitmap(selectedBitmap);
            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
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

                Zapis.zapis();
                Toast.makeText(getApplicationContext(),"zapisano kurwo", Toast.LENGTH_LONG).show();
                MediaStore.Images.Media.insertImage(getContentResolver(), Photography.getInstance().getPhoto(), "moje foto", "zapis");

                return true;

            case R.id.przyciecie:

                //Intent cropIntent = new Intent("com.android.camera.action.CROP");
                //startActivityForResult(cropIntent, PICK_CROP);
                przcinanko(getImageUri(this.getApplicationContext(),Photography.getInstance().getPhoto()));
               // return true;

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

    public void przcinanko(Uri picuri)
    {


        try {

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picuri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PICK_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
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



