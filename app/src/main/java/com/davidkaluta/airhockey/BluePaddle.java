package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BluePaddle extends RoundEntity implements Runnable {

    public BluePaddle(int x, int y, HockeyTable ht) {
        super(x, y, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(ht.getResources(), R.drawable.blue_paddle), 128,128,true));
        Thread thread = new Thread(this,  "aiThread");
        thread.start();
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(2);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
