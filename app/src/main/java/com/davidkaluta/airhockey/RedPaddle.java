package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RedPaddle extends RoundEntity {

    public Goal goal;
    private boolean isWinner;

    public RedPaddle(int x, int y, Goal goal, HockeyTable ht) {
        super(x, y, 
        	Bitmap.createScaledBitmap(
        	BitmapFactory.decodeResource(
        	ht.getResources(), R.drawable.red_paddle), 128,128,true));
        this.goal = goal;
    }

    public Goal getGoal() {
        return goal;
    }


    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}
