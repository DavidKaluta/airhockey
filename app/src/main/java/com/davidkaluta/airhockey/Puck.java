package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.davidkaluta.airhockey.GameActivity.deviceHeight;
import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

/**
 * The puck
 *
 * @author David Kaluta
 * @version 22
 * @since 1
 */
public class Puck extends RoundEntity implements Runnable {

    /**
     * The puck's velocity on the X-axis
     */
    private float dx;

    /**
     * The puck's velocity on the Y-axis
     */
    private float dy;

    /**
     * A HockeyTable to get the Paddles
     */
    private HockeyTable ht;

    /**
     * Create a Puck
     *
     * @param x  X-position for the puck
     * @param y  Y-position for the puck
     * @param ht The HockeyTable
     */
    Puck(float x, float y, HockeyTable ht) {
        super(x, y,
                Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(ht.getResources(),
                                R.drawable.puck),
                        64, 64, true));
        Thread thread = new Thread(this, "PuckThread");
        this.ht = ht;
        dx = 0;
        dy = 5;
        thread.start();
    }

    /**
     * The main loop for the puck's movement
     */
    @Override
    public void run() {
        while (!ht.getRP().isWinner() && !ht.getBP().isWinner()) {
            if (x <= 0 || x >= deviceWidth - radius * 2)
                dx = -dx;
            if (x < 0)
                x = 0;
            if (x > deviceWidth - radius * 2)
                x = deviceWidth - radius * 2;
            if (y <= 0 || y >= deviceHeight - radius * 2)
                dy = -dy;
            RedPaddle rp = ht.getRP();
            System.out.println(distanceFrom(rp));
            if (distanceFrom(rp) <= radius + rp.radius) {
                float a = (rp.centerPointY - centerPointY)
                        / (rp.centerPointX - centerPointX);
                float b = centerPointX - centerPointX * a;
                float xStart = -b / a;
                double tan;
                if (xStart < 0) {
                    tan = centerPointX / (centerPointY - b);
                } else {
                    tan = (centerPointX - xStart) / centerPointY;
                }
                double radian1 = Math.atan(tan);
                double radian2 = Math.acos(dy / 5);
                double angle = radian1 + radian2;
                double anotherRadian = Math.PI / 2 - angle;
                float dxAdjusted = 5 * (float) Math.cos(anotherRadian);
                float dyAdjusted = 5 * (float) Math.sin(anotherRadian);
                dx = dxAdjusted * (float) Math.sin(anotherRadian);
                if (centerPointY > rp.centerPointY)
                    dy = dyAdjusted * (float) Math.cos(angle);
                else if (centerPointY < rp.centerPointY)
                    dy = -dyAdjusted * (float) Math.cos(angle);
                else
                    dy = 0;
            }
            BluePaddle bp = ht.getBP();
            if (bp != null) {
                if (distanceFrom(bp) <= radius + bp.radius) {
                    float a;
                    float b;
                    float xStart;
                    if (bp.centerPointX - centerPointX != 0) {
                        a = (bp.centerPointY - centerPointY)
                                / (bp.centerPointX - centerPointX);
                        b = centerPointX - centerPointX * a;
                        xStart = -b / a;
                        double tan;
                        if (xStart < 0) {
                            tan = centerPointX / (centerPointY - b);
                        } else {
                            tan = (centerPointX - xStart) / centerPointY;
                        }
                        double radian1 = Math.atan(tan);
                        double radian2 = Math.acos(dy / 5);
                        double angle = radian1 + radian2;
                        double anotherRadian = Math.PI / 2 - angle;
//                        float dxAdjusted =
                                5 * (float) Math.cos(anotherRadian);
                        float dyAdjusted =
                                5 * (float) Math.sin(anotherRadian);
                        dx = dxAdjusted * (float) Math.sin(anotherRadian);
                        if (centerPointY < bp.centerPointY)
                            dy = -dyAdjusted * (float) Math.cos(angle);
                        else if (centerPointY > bp.centerPointY)
                            dy = dyAdjusted * (float) Math.cos(angle);
                        else
                            dy = 0;
                    } else {
                        dy = -dy;
                    }

                }
            }
            if (bp != null) {
                if (rp.getGoal() != null && bp.getGoal() != null) {
                    if (x + radius * 2 > rp.getGoal().x
                            && x < rp.getGoal().x + deviceWidth / 2
                            && y < 10) {
                        rp.getGoal().incScore();
                        centerPointX = deviceWidth / 2;
                        x = centerPointX - radius;
                        centerPointY = deviceHeight / 2;
                        y = centerPointY - radius;
                        if (rp.getGoal().getScore() == 10) {
                            rp.win();
                            Saver.setWins(ht.getContext(), Saver.getWins(
                                    ht.getContext()) + 1);
                        }
                    } else if (x + radius * 2 > bp.getGoal().x
                            && x < bp.getGoal().x + deviceWidth / 2
                            && y + radius * 2 > deviceHeight - 10) {
                        bp.getGoal().incScore();
                        centerPointX = deviceWidth / 2;
                        x = centerPointX - radius;
                        centerPointY = deviceHeight / 2;
                        y = centerPointY - radius;
                        if (bp.getGoal().getScore() == 10) {
                            bp.win();
                        }
                    }
                }
            }
            if (dx == 0 && dy == 0) {
                centerPointX = deviceWidth / 2;
                x = centerPointX - radius;
                centerPointY = deviceHeight / 2;
                y = centerPointY - radius;
                dy = 5;
                dx = 0;
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
