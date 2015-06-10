package com.example.michal.projektwzorce.presenter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
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

                //facebook();
                ShareContext shareContex = new ShareContext();
                shareContex.getShareIntent("facebook");
                return true;




            case R.id.mail:


                Resources resources = getResources();

                Intent emailIntent = new Intent();
                emailIntent.setAction(Intent.ACTION_SEND);
                // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
                emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.share_email_native)));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_email_subject));
                emailIntent.setType("message/rfc822");

                PackageManager pm = getPackageManager();
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");


                Intent openInChooser = Intent.createChooser(emailIntent, resources.getString(R.string.share_chooser_text));

                List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
                List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
                for (int i = 0; i < resInfo.size(); i++) {
                    // Extract the label, append it, and repackage it in a LabeledIntent
                    ResolveInfo ri = resInfo.get(i);
                    String packageName = ri.activityInfo.packageName;
                    if(packageName.contains("android.email")) {
                        emailIntent.setPackage(packageName);
                    } else if(packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms") || packageName.contains("android.gm")) {
                        Intent intent2 = new Intent();
                        intent2.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                        intent2.setAction(Intent.ACTION_SEND);
                        intent2.setType("text/plain");
                        if(packageName.contains("twitter")) {
                            intent2.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_twitter));
                        } else if(packageName.contains("facebook")) {
                            // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                            // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                            // will show the <meta content ="..."> text from that page with our link in Facebook.
                            intent2.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_facebook));
                        } else if(packageName.contains("mms")) {
                            intent.putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.share_sms));
                        } else if(packageName.contains("android.gm")) {
                            intent2.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(resources.getString(R.string.share_email_gmail)));
                            intent2.putExtra(Intent.EXTRA_SUBJECT, resources.getString(R.string.share_email_subject));
                            intent2.setType("message/rfc822");
                        }

                        intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
                    }
                }

                // convert intentList to array
                LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);

                openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
                startActivity(openInChooser);




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





   /* public void share()
    {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        Uri image = Uri.parse(("android.resource://" + getPackageName() + "/" + Photography.getInstance().getPhoto()));
        sharingIntent.setType("image/png");
        sharingIntent.putExtra(Intent.EXTRA_STREAM, image);
        startActivity(Intent.createChooser(sharingIntent, "Share image using"));

    } */
}



