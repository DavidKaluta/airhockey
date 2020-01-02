package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * A player-controlled red paddle
 *
 * @author David Kaluta
 * @version 21
 * @since 1
 */
class RedPaddle extends RoundEntity {

    /**
     * a Goal for the paddle
     */
    private Goal goal;

    /**
     * A variable showing if the player won
     */
    private boolean isWinner;

    public int delay;

    /**
     * Create a new Red Paddle
     *
     * @param x    the paddle's x-coordinate
     * @param y    the paddle's y-coordinate
     * @param goal a Goal for the paddle
     * @param ht   a HockeyTable for resource getting
     */
    RedPaddle(int x, int y, Goal goal, HockeyTable ht) {
        super(x, y, 2.5f,
                Bitmap.createScaledBitmap(
                        BitmapFactory.decodeResource(
                                ht.getResources(), R.drawable.red_paddle),
                        128, 128, true));
        isWinner = false;
        this.goal = goal;
        delay = 0;
    }

    /**
     * Get the paddle's goal
     *
     * @return the goal
     */
    Goal getGoal() {
        return goal;
    }

    /**
     * Check if the red paddle won
     *
     * @return if the red paddle won
     */
    boolean isWinner() {
        return isWinner;
    }

    /**
     * Win the game for the red paddle
     */
    void win() {
        isWinner = true;
    }
}
