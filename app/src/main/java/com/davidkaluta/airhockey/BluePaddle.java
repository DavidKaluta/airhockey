package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

public class BluePaddle extends RoundEntity implements Runnable {

    private Thread thread;
    private HockeyTable ht;
    private Goal goal;
    private boolean isWinner;

    double v;

    public BluePaddle(int x, int y, double v, Goal goal, HockeyTable ht) {
        super(x, y, 
        	Bitmap.createScaledBitmap(
        	BitmapFactory.decodeResource(
        	ht.getResources(), R.drawable.blue_paddle), 128,128,true));
        this.v = v;
        this.ht = ht;
        this.goal = goal;
        thread = new Thread(this,  "aiThread");
        thread.start();
    }

    public Goal getGoal() {
        return goal;
    }



    public void run() {
        while(true) {
            Puck puck = ht.getP();
            if(puck != null) {
                if (v != 123) {
                    if (puck.getCenterPointX() > centerPointX &&
                     centerPointX + radius < deviceWidth) {
                        x += v;
                        centerPointX += v;
                    } else if (centerPointX - radius > 0) {
                        x -= v;
                        centerPointX -= v;
                    }
                } else {
                    centerPointX = puck.centerPointX;
                    x = centerPointX - radius;
                }
            }
            try {
                Thread.sleep(2);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

}
