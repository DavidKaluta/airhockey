package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static com.davidkaluta.airhockey.GameActivity.deviceWidth;

/**
 * An AI-controlled blue paddle
 *
 * @author David Kaluta
 * @version 24
 * @since 2
 */
public class BluePaddle extends RoundEntity implements Runnable {

    /**
     * The HockeyTable to track the puck on
     */
    private HockeyTable ht;

    /**
     * The paddle's goal
     */
    private Goal goal;

    /**
     * The NPC's player condition
     */
    private boolean isWinner;

    /**
     * The paddle's velocity (controlled by difficulty)
     */
    private double v;

    public int delay;
    
    /**
     * The preferred Y coordinate for the blue paddle
     */
    private float starterY;

    /**
     * Create a new blue paddle
     *
     * @param x    The paddle's x-coordinate
     * @param y    The paddle's y-coordinate
     * @param v    The paddle's velocity
     * @param goal The paddle's goal
     * @param ht   The HockeyTable
     */
    BluePaddle(int x, int y, double v, Goal goal, HockeyTable ht) {
        super(x, y, 2.5f,
                Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(
                                ht.getResources(), R.drawable.blue_paddle),
                        128, 128, true));
        this.v = v;
        starterY = y;
        this.ht = ht;
        this.goal = goal;
        isWinner = false;
        delay = 0;
        Thread thread = new Thread(this, "aiThread");
        thread.start();
    }

    /**
     * Get the paddle's goal
     *
     * @return the paddle's goal
     */
    Goal getGoal() {
        return goal;
    }


    /**
     * The paddle AI's loop
     */
    @Override
    public void run() {
        while (true) {
            Puck puck = ht.getP();
            if (puck != null) {
                if (v != 123) {
                    if (delay == 0) {
                        if (centerPointY < puck.getCenterPointY()) {
                            if (puck.getCenterPointX() > centerPointX &&
                                    centerPointX + radius < deviceWidth) {
                                x += v;
                                centerPointX += v;
                            } else if (centerPointX - radius > 0) {
                                x -= v;
                                centerPointX -= v;
                            }
                            if (starterY > centerPointY) {
                                y += v;
                                centerPointY += v;
                            }
                        } else if (centerPointY > 0) {
                            y -= v;
                            centerPointY -= v;
                        }
                    }
                }
                else {
                    centerPointX = puck.centerPointX;
                    x = centerPointX - radius;
                }
            }
            try {
                Thread.sleep(2);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check the paddle's winning condition
     *
     * @return the paddle's winning condition
     */
    boolean isWinner() {
        return isWinner;
    }

    /**
     * Win the game for NPC
     */
    void win() {
        isWinner = true;
    }

}
