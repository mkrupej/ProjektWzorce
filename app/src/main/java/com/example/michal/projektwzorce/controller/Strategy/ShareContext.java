package com.example.michal.projektwzorce.controller.Strategy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.example.michal.projektwzorce.R;
import com.example.michal.projektwzorce.presenter.PictureEdit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krzysztof on 2015-06-10.
 */
public class ShareContext
{
    ShareStrategy shareStrategy;

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    private String nazwa;
    private String imagePath;


    public void setShareStrategy(ShareStrategy shareStrategy) {
        this.shareStrategy = shareStrategy;
    }



    public List<Intent> share(String nameApp, String imagePath, List<ResolveInfo> resInfo) {

        PictureEdit pictureEdit2 = new PictureEdit();

        List<Intent> targetedShareIntents = new ArrayList<Intent>();
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("image/*");
      
        if (!resInfo.isEmpty()) {
            for (ResolveInfo info : resInfo) {
                Intent targetedShare = new Intent(android.content.Intent.ACTION_SEND);
                targetedShare.setType("image/*");
                if (info.activityInfo.packageName.toLowerCase().contains(nameApp) || info.activityInfo.name.toLowerCase().contains(nameApp)) {
                    targetedShare.putExtra(Intent.EXTRA_SUBJECT, "Sample Photo");
                    targetedShare.putExtra(Intent.EXTRA_TEXT, "This photo is created by App Name");
                    targetedShare.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(imagePath)));
                    targetedShare.setPackage(info.activityInfo.packageName);
                    targetedShareIntents.add(targetedShare);
                }
            }

            return targetedShareIntents;
        }
        return null;
    }







}
