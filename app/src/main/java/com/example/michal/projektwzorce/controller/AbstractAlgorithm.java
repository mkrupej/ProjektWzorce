package com.example.michal.projektwzorce.controller;

import android.graphics.Bitmap;

/**
 * Created by Michal on 2015-06-09.
 */
public abstract class AbstractAlgorithm {

    protected int width;
    protected int height;

    abstract protected Bitmap getAlgorithm(Bitmap b);

    public void getCoop(Bitmap b) {

        height = b.getHeight();
        width = b.getWidth();
    }

    public Bitmap calculate(Bitmap b){
        return getAlgorithm(b);
    }




}
