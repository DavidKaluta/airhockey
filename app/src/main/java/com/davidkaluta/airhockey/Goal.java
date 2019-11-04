package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

class Goal extends Entity {
    private int score;

    Goal(float x, float y, HockeyTable ht) {
        super(x,y, Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(ht.getResources()
                        , R.drawable.white_pixel),
                (int) deviceWidth/2, 10, true));
        score = 0;
    }

    int getScore() {
        return score;
    }

    void incScore() {
        score++;
    }

}
