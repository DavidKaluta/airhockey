package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

/**
 * A Goal
 * @author  David Kaluta
 * @version 18
 * @since   5
 */
class Goal extends Entity {
    /**
     * The goal's score
     */
    private int score;

    /**
     * Create a new goal
     * @param x     the goal's x-coordinate
     * @param y     The goal's y-coordinate
     * @param ht    A HockeyTable for resources
     */
    Goal(float x, float y, HockeyTable ht) {
        super(x,y, Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(ht.getResources()
                        , R.drawable.white_pixel),
                (int) deviceWidth/2, 10, true));
        score = 0;
    }

    /**
     * Get the goal's score
     * @return  the goal's score
     */
    int getScore() {
        return score;
    }

    /**
     * Increase the score by 1
     */
    void incScore() {
        score++;
    }

}
