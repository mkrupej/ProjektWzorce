package com.example.michal.projektwzorce.controller.TemplateMethod;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

import com.example.michal.projektwzorce.controller.TemplateMethod.AbstractAlgorithm;

/**
 * Created by Krzysztof on 2015-06-09.
 */
public class NegatywAlgoritthm extends AbstractAlgorithm
{
    public Bitmap getAlgorithm(Bitmap sampleBitmap){
        ColorMatrix negativeMatrix =new ColorMatrix();
        float[] negMat={-1, 0, 0, 0, 255, 0, -1, 0, 0, 255, 0, 0, -1, 0, 255, 0, 0, 0, 1, 0 };
        negativeMatrix.set(negMat);
        final ColorMatrixColorFilter colorFilter= new ColorMatrixColorFilter(negativeMatrix);
        Bitmap rBitmap = sampleBitmap.copy(Bitmap.Config.ARGB_8888, true);
        Paint paint=new Paint();
        paint.setColorFilter(colorFilter);
        Canvas myCanvas =new Canvas(rBitmap);
        myCanvas.drawBitmap(rBitmap, 0, 0, paint);
        return rBitmap;
    }
}
