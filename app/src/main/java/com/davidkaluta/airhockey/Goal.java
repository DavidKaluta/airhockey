package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

public class Goal extends Entity {
    private int score;

    public Goal(float x, float y, HockeyTable ht) {
        super(x,y, Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(ht.getResources()
                        , R.drawable.white_pixel),
                deviceWidth/2, 10, true));
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void incScore() {
        score++;
    }

}
