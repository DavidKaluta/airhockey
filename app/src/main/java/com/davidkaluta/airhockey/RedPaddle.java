package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class RedPaddle extends RoundEntity {

    private Goal goal;
    private boolean isWinner;

    RedPaddle(int x, int y, Goal goal, HockeyTable ht) {
        super(x, y, 
        	Bitmap.createScaledBitmap(
        	BitmapFactory.decodeResource(
        	ht.getResources(), R.drawable.red_paddle), 128,128,true));
        isWinner = false;
        this.goal = goal;
    }

    Goal getGoal() {
        return goal;
    }


    boolean isWinner() {
        return isWinner;
    }

    void win() {
        isWinner = true;
    }
}
