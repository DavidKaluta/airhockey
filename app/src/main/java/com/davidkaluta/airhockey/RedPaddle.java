package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RedPaddle extends RoundEntity {

    public RedPaddle(int x, int y, HockeyTable ht) {
        super(x, y, 
        	Bitmap.createScaledBitmap(
        	BitmapFactory.decodeResource(
        	ht.getResources(), R.drawable.red_paddle), 128,128,true));
    }

}
