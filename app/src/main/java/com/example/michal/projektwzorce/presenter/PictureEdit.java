package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.MenuItem;


import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.controller.TemplateMethod.AbstractAlgorithm;
import com.example.michal.projektwzorce.controller.TemplateMethod.BlackWhiteAlgorithm;
import com.example.michal.projektwzorce.controller.TemplateMethod.BlurAlgorithm;
import com.example.michal.projektwzorce.controller.TemplateMethod.NegatywAlgoritthm;
import com.example.michal.projektwzorce.controller.TemplateMethod.SepiaAlgoritm;
import com.example.michal.projektwzorce.controller.Zapis;
import com.example.michal.projektwzorce.model.Photography;

public class PictureEdit extends Activity  {

    // private static Photography photo =  Photography.getInstance();

    ImageView Picture;

    protected  void onResume(){
        super.onResume();
        Picture.setImageBitmap(Photography.getInstance().getPhoto());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picture_edit);
        getActionBar().setDisplayShowTitleEnabled(false);
        Picture = (ImageView) findViewById(R.id.Pic);

        if(Photography.getInstance().getCopy() == null) {
            Picture.setImageBitmap(Photography.getInstance().getPhoto());
        }
        else
        {
            Picture.setImageBitmap(Photography.getInstance().getCopy());
        }

        Picture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(PictureEdit.this, Zoom.class);
                startActivity(intent);
            }});


        ImageButton czarnoBialy;
        ImageButton sepia;
        ImageButton negatyw;
        ImageButton blur;

        czarnoBialy = (ImageButton) findViewById(R.id.imageButtonBlackWhite);
        sepia = (ImageButton) findViewById(R.id.imageButtonSepia);
        negatyw = (ImageButton) findViewById(R.id.imageButtonFilter3);
        blur = (ImageButton) findViewById(R.id.imageButtonFilter4);


        AbstractAlgorithm bw = new BlackWhiteAlgorithm();
        AbstractAlgorithm sp = new SepiaAlgoritm();
        AbstractAlgorithm ng = new NegatywAlgoritthm();
        AbstractAlgorithm bl = new BlurAlgorithm();

        czarnoBialy.setImageBitmap(bw.calculate(Photography.getInstance().getPhoto()));
        czarnoBialy.setScaleType(ImageView.ScaleType.CENTER_CROP);

        sepia.setImageBitmap(sp.calculate(Photography.getInstance().getPhoto()));
        sepia.setScaleType(ImageView.ScaleType.CENTER_CROP);

        negatyw.setImageBitmap(ng.calculate(Photography.getInstance().getPhoto()));
        negatyw.setScaleType(ImageView.ScaleType.CENTER_CROP);

        blur.setImageBitmap(bl.calculate(Photography.getInstance().getPhoto()));
        blur.setScaleType(ImageView.ScaleType.CENTER_CROP);

        czarnoBialy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                AbstractAlgorithm bw = new BlackWhiteAlgorithm();
                Bitmap myImage = bw.calculate(Photography.getInstance().getPhoto());
                Picture.setImageBitmap(myImage);
                Photography.getInstance().setCopy(myImage);
            }
              });

        sepia.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                AbstractAlgorithm sp = new SepiaAlgoritm();
                Bitmap myImage = sp.calculate(Photography.getInstance().getPhoto());
                Picture.setImageBitmap(myImage);
                Photography.getInstance().setCopy(myImage);

            }});

        negatyw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                AbstractAlgorithm ng = new NegatywAlgoritthm();
                Bitmap myImage = ng.calculate(Photography.getInstance().getPhoto());
                Picture.setImageBitmap(myImage);
                Photography.getInstance().setCopy(myImage);

            }});

        blur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                AbstractAlgorithm bl = new BlurAlgorithm();
                Bitmap myImage = bl.calculate(Photography.getInstance().getPhoto());
                Picture.setImageBitmap(myImage);
                Photography.getInstance().setCopy(myImage);

            }});
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
                Toast.makeText(getApplicationContext(),"zapisano ", Toast.LENGTH_LONG).show();
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



