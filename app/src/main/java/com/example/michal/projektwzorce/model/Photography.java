package com.example.michal.projektwzorce.model;

import android.graphics.Bitmap;
import android.provider.ContactsContract;

/**
 * Created by Michal on 2015-05-18.
 */
public class Photography {

    private static Photography instance;
    private String selectedImagePath;

    private Bitmap photography;

    private Photography(){

        photography = null;
    }

    public static Photography getInstance() {
        if (instance == null)
            instance = new Photography();
            return new Photography();
    }

    public  Bitmap getPhoto(){

        return photography;
    }

    public void setPhoto(Bitmap photo){

        photography = photo;

    }

    public String getPath()
    {
        return selectedImagePath;
    }
    public void setPath(String path){
        selectedImagePath = path;
    }



}
