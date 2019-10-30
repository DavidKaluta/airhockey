package com.davidkaluta.airhockey;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BluePaddle extends RoundEntity implements Runnable {

    Thread thread;
    HockeyTable ht;
    Goal goal;

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
            int deviceWidth = Resources.getSystem().getDisplayMetrics()
                    .widthPixels;
            if(v != 123) {
                if (puck.getCenterPointX() > centerPointX && centerPointX + radius < deviceWidth) {
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
            try {
                Thread.sleep(2);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
