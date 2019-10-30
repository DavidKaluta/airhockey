package com.davidkaluta.airhockey;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.davidkaluta.airhockey.GameActivity.deviceHeight;
import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

public class Puck extends RoundEntity implements Runnable {

    private float dx;
    private float dy;
    private HockeyTable ht;
    private Thread thread;

    public Puck(float x, float y, HockeyTable ht) {
        super(x, y, 
        	Bitmap.createScaledBitmap(
        	BitmapFactory.decodeResource(ht.getResources(),R.drawable.puck), 64,64,true));
        thread = new Thread(this,"PuckThread");
        this.ht = ht;
        dx = 0;
        dy = 5;
        thread.start();
    }

    @Override
    public void run() {
        while(!ht.getRP().isWinner() && !ht.getBP().isWinner()) {
            if (x <= 0 || x >= deviceWidth - radius * 2)
                dx = -dx;
            if(x < 0)
                x = 0;
            if(x > deviceWidth - radius * 2)
                x = deviceWidth-radius * 2;
            if (y <= 0 || y >= deviceHeight - radius * 2)
                dy = -dy;
            RedPaddle rp = ht.getRP();
            System.out.println(distanceFrom(rp));
            if(distanceFrom(rp)<= radius + rp.radius) {
                //TODO: make collision detection nicer
                float a = (rp.centerPointY - centerPointY) 
                / (rp.centerPointX - centerPointX);
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
                double radian2 = Math.acos(dy/5);
                double angle = radian1 + radian2;
                double anotherRadian = Math.PI/2 - angle;
                float dxAdjusted =  5*(float) Math.cos(anotherRadian);
                float dyAdjusted = 5*(float) Math.sin(anotherRadian);
                dyAdjusted = -dyAdjusted;
                dx = dxAdjusted * (float)Math.sin(anotherRadian);
                dy = dyAdjusted * (float)Math.cos(angle);
            }
            BluePaddle bp = ht.getBP();
            if(bp != null) {
                if (distanceFrom(bp) <= radius + bp.radius) {
                    //TODO: make collision detection nicer
                    float a = (rp.centerPointY - centerPointY)
                            / (rp.centerPointX - centerPointX);
                    float b = centerPointX - centerPointX * a;
                    float xstart = -b / a;
                    double tan;
                    if (xstart < 0) {
                        float ystart = b;
                        tan = centerPointX / (centerPointY - ystart);
                    } else {
                        tan = (centerPointX - xstart) / centerPointY;
                    }
                    double radian1 = Math.atan(tan);
                    double radian2 = Math.acos(dy / 5);
                    double angle = radian1 + radian2;
                    double anotherRadian = Math.PI / 2 - angle;
                    float dxAdjusted = 5 * (float) Math.cos(anotherRadian);
                    float dyAdjusted = 5 * (float) Math.sin(anotherRadian);
                    dyAdjusted = -dyAdjusted;
                    dx = dxAdjusted * (float) Math.sin(anotherRadian);
                    dy = -dyAdjusted * (float) Math.cos(angle);
                }
            }
            if(rp.getGoal() != null && bp.getGoal() != null) {
                if (x + radius * 2 > rp.getGoal().x
                        && x < rp.getGoal().x + deviceWidth / 2
                        && y < 10) {
                    rp.getGoal().incScore();
                    centerPointX = deviceWidth / 2;
                    x = centerPointX - radius;
                    centerPointY = deviceHeight / 2;
                    y = centerPointY - radius;
                    if (rp.getGoal().getScore() == 10) {
                        rp.setWinner(true);
                    }
                } else if (x + radius * 2 > bp.getGoal().x
                        && x < bp.getGoal().x + deviceWidth / 2
                        && y + radius*2 > deviceHeight - 10) {
                    bp.getGoal().incScore();
                    centerPointX = deviceWidth / 2;
                    x = centerPointX - radius;
                    centerPointY = deviceHeight / 2;
                    y = centerPointY - radius;
                    if (bp.getGoal().getScore() == 10) {
                        bp.setWinner(true);
                    }
                }
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
