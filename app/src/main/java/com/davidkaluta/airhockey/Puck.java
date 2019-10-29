package com.davidkaluta.airhockey;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class Puck extends RoundEntity implements Runnable {

    private float dx;
    private float dy;
    private HockeyTable ht;
    private Thread thread;

    public Puck(float x, float y, HockeyTable ht) {
        super(x, y, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(ht.getResources(),R.drawable.puck), 64,64,true));
        thread = new Thread(this,"PuckThread");
        this.ht = ht;
        dx = 0;
        dy = 1;
        thread.start();
    }

    @Override
    public void run() {
        while(true) {
            int deviceWidth = Resources.getSystem().getDisplayMetrics()
                    .widthPixels;
            int deviceHeight = Resources.getSystem().getDisplayMetrics()
                    .heightPixels;
            if (x <= 0 || x >= deviceWidth - radius * 2)
                dx = -dx;
            if(x < 0)
                x = 0;
            if(x > deviceWidth - radius * 2)
                x = deviceWidth-radius * 2;
            if (y <= 0 || y >= deviceHeight - radius * 2)
                dy = -dy;
            RedPaddle rp = ht.getRP();
            Log.d(TAG, "radius+rp.radius="+radius+rp.radius+", distanceFrom(rp)="+distanceFrom(rp));
            if(distanceFrom(rp)<= radius + rp.radius) {
                //TODO: add collision detection
                /*float a = (rp.centerPointY - centerPointY) / (rp.centerPointX - centerPointX);
                float b = centerPointX - centerPointX*a;
                float xstart = -b/a;
                double tan;
                if(xstart < 0) {
                    float ystart = b;
                    tan = centerPointX/(centerPointY-ystart);
                }
                else {
                    tan = (centerPointX-xstart)/centerPointY;
                }
                double radian1 = Math.atan(tan);
                double radian2 = Math.acos(dy/1);
                double angle = radian1 + radian2;
                double anotherRadian = Math.PI/2 - angle;
                float dxAdjusted =  (float) Math.cos(anotherRadian);
                float dyAdjusted = (float) Math.sin(anotherRadian);
                dyAdjusted = -dyAdjusted;
                dx = dxAdjusted * (float)Math.cos(anotherRadian);
                dy = dyAdjusted * (float)Math.sin(anotherRadian);*/
                dx = 0;
                dy = 0;
            }
            x += dx;
            centerPointX += dx;
            y += dy;
            centerPointY += dy;
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
