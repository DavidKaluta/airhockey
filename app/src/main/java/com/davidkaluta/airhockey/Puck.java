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
     * The previous coordinates of the red paddle
     */
    private float[] rpPrevCoords;

    /**
     * The previous coordinates of the blue paddle
     */
    private float[] bpPrevCoords;

    /**
     * Create a Puck
     *
     * @param x  X-position for the puck
     * @param y  Y-position for the puck
     * @param ht The HockeyTable
     */
    Puck(float x, float y, HockeyTable ht) {
        super(x, y, 0.5f,
                Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(ht.getResources(),
                                R.drawable.puck),
                        64, 64, true));
        Thread thread = new Thread(this, "PuckThread");
        rpPrevCoords = new float[2];
        bpPrevCoords = new float[2];
        this.ht = ht;
        dx = 0;
        dy = 0;
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
            if(rp != null) {
            	if (distanceFrom(rp) - 32 <= radius + rp.radius) {
                    float[] rpCurrentCoords = {rp.getX(), rp.getY()};
                    float rpDx = (rpCurrentCoords[0] - rpPrevCoords[0]);
                    float rpDy = (rpCurrentCoords[1] - rpPrevCoords[1]);
                    if (rpDx > 0) {
                        dx = (mass * dx + rp.getMass() *
                                rpDx - rp.getMass() * (rpDx - 0.5f)) / mass;
                    }
                    else if (rpDx < 0) {
                        dx = (mass * dx + rp.getMass() *
                                rpDx - rp.getMass() * (rpDx + 0.5f)) / mass;
                    }
                    else {
                        dx = (mass * dx + rp.getMass() *
                                rpDx - rp.getMass() * rpDx) / mass;
                    }
                    if (centerPointX < rp.centerPointX)
                        dx = -dx;
                    dy = (mass * dy + rp.getMass() *
                            rpDy - rp.getMass() * (rpDy - 0.5f)) / mass;
                    if (centerPointY < rp.centerPointY)
                        dy = -dy;
                    dx /= 1.25f;
                    dy /= 1.25f;
            	}
            }
            BluePaddle bp = ht.getBP();
            if (bp != null) {
                if (distanceFrom(bp) <= radius + bp.radius) {
                    float[] bpCurrentCoords = {bp.getX(), bp.getY()};
                    float bpDx = (bpCurrentCoords[0] - bpPrevCoords[0]);
                    float bpDy = (bpCurrentCoords[1] - bpPrevCoords[1]);
                    if (bpDx > 0) {
                        dx = (mass * dx + bp.getMass() *
                                bpDx - bp.getMass() * (bpDx - 0.5f)) / mass;
                    }
                    else if(bpDx < 0) {
                        dx = (mass * dx + bp.getMass() *
                                bpDx - bp.getMass() * (bpDx + 0.5f)) / mass;
                    }
                    else {
                        dx = (mass * dx + bp.getMass() *
                                bpDx - bp.getMass() * bpDx) / mass;
                    }
                    if (centerPointX < bp.centerPointX)
                        dx = -dx;
                    dy = (mass * dy + bp.getMass() *
                            bpDy - rp.getMass() * (bpDy - 0.5f)) / mass;
                    if (centerPointY < bp.centerPointY)
                        dy = -dy;
                    dx /= 1.25f;
                    dy /= 1.25f;
                }
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
            if (rp != null) {
                rpPrevCoords[0] = rp.getX();
                rpPrevCoords[1] = rp.getY();
            }
            if (bp != null) {
                bpPrevCoords[0] = bp.getX();
                bpPrevCoords[1] = bp.getY();
            }
            x += dx;
            centerPointX += dx;
            y += dy;
            centerPointY += dy;
            try {
                Thread.sleep(2);
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
