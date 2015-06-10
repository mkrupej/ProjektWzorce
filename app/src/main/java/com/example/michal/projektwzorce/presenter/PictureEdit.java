package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.MenuItem;


import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.controller.Strategy.ShareContext;
import com.example.michal.projektwzorce.controller.TemplateMethod.AbstractAlgorithm;
import com.example.michal.projektwzorce.controller.TemplateMethod.BlackWhiteAlgorithm;
import com.example.michal.projektwzorce.controller.TemplateMethod.BlurAlgorithm;
import com.example.michal.projektwzorce.controller.TemplateMethod.NegatywAlgoritthm;
import com.example.michal.projektwzorce.controller.TemplateMethod.SepiaAlgoritm;
import com.example.michal.projektwzorce.controller.Zapis;
import com.example.michal.projektwzorce.model.Photography;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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


            case R.id.home:

                home();

                return true;

            case R.id.facebook:
                ShareContext shareContext = new ShareContext();
                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                OutputStream outStream = null;
                File file = new File(extStorageDirectory, "MAIL.PNG");
                try {
                    outStream = new FileOutputStream(file);
                    Photography.getInstance().getPhoto().compress(Bitmap.CompressFormat.PNG, 100, outStream);
                    outStream.flush();
                    outStream.close();
                }
                catch(Exception e)
                {}

               shareContext.share("facebook", file.toString());



            case R.id.mail:




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

    public void facebook()
    {

    }

    public void share2(String nameApp, String imagePath) {
        try
        {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("image/*");
            List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(share, 0);
            if (!resInfo.isEmpty()){
                for (ResolveInfo info : resInfo) {
                    Intent targetedShare = new Intent(android.content.Intent.ACTION_SEND);
                    targetedShare.setType("image/*"); // put here your mime type
                    if (info.activityInfo.packageName.toLowerCase().contains(nameApp) || info.activityInfo.name.toLowerCase().contains(nameApp)) {
                        targetedShare.putExtra(Intent.EXTRA_SUBJECT, "Sample Photo");
                        targetedShare.putExtra(Intent.EXTRA_TEXT,"This photo is created by App Name");
                        targetedShare.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(imagePath)) );
                        targetedShare.setPackage(info.activityInfo.packageName);
                        targetedShareIntents.add(targetedShare);
                    }
                }
                Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Wybierz aplikacjê.");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
                startActivity(chooserIntent);
            }
        }
        catch(Exception e){
            Log.v("VM", "Exception while sending image on" + nameApp + " " + e.getMessage());
        }
    }




   /* public void share()
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        Uri image = Uri.parse(("android.resource://" + getPackageName() + "/" + Photography.getInstance().getPhoto()));
        sharingIntent.setType("image/png");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, image);
        startActivity(Intent.createChooser(sharingIntent, "Share image using"));

    } */
}



