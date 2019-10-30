package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Goal extends Entity {
    int score;

    public Goal(float x, float y, HockeyTable ht) {
        super(x,y, Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(ht.getResources()
                        , R.drawable.white_pixel),
                GameActivity.deviceWidth/2, 10, true));
        score = 0;
    }

}
