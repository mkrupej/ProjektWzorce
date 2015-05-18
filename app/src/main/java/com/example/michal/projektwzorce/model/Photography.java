package com.example.michal.projektwzorce.model;

import android.graphics.Bitmap;

/**
 * Created by Michal on 2015-05-18.
 */
public class Photography {

    private static Photography instance;
    private String selectedImagePath;

    private Bitmap Picture;

    private Photography(){
    }

    public static Photography getInstance() {
        if (instance == null)
            instance = new Photography();
            return instance;
    }

    public  Bitmap getPhoto(){

        return Picture;
    }

    public void setPhoto(Bitmap photo){

        Picture = photo;

    }

    public String getPath()
    {
        return selectedImagePath;
    }
    public void setPath(String path){
        selectedImagePath = path;
    }


}
