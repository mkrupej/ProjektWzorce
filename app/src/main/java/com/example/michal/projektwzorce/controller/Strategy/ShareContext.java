package com.example.michal.projektwzorce.controller.Strategy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krzysztof on 2015-06-10.
 */
public class ShareContext extends Activity
{
    ShareStrategy shareStrategy;

    private String nazwa;

    public void setShareStrategy(ShareStrategy shareStrategy) {
        this.shareStrategy = shareStrategy;
    }

    public void share(String nameApp, String imagePath)
    {
        //mk ³eb
        try
        {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("image/*");
            List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(share, 0);
            if (!resInfo.isEmpty()){
                for (ResolveInfo info : resInfo) {
                    Intent targetedShare = new Intent(android.content.Intent.ACTION_SEND);
                    targetedShare.setType("image/*");
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
}
