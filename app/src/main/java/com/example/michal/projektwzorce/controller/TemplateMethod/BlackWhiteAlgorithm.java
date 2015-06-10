package com.example.michal.projektwzorce.controller.TemplateMethod;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import com.example.michal.projektwzorce.controller.TemplateMethod.AbstractAlgorithm;

/**
 * Created by Michal on 2015-06-09.
 */
public class BlackWhiteAlgorithm extends AbstractAlgorithm
{

    protected Bitmap getAlgorithm(Bitmap b){

        getCoop(b);
        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(b, 0, 0, paint);
        return bmpGrayscale;
    };


}
