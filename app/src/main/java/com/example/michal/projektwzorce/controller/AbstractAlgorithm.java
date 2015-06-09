package com.example.michal.projektwzorce.controller;

import android.graphics.Bitmap;

/**
 * Created by Michal on 2015-06-09.
 */
public abstract class AbstractAlgorithm {


    final public Bitmap algorithm() {
        if (this.getA() != 5) return null;
        else if (this.getB() == 9) return null;
        else return null;
    }

    abstract protected int getA();
    abstract protected int getB();


}
