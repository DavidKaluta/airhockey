package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * The parent class for all entities on the Game Board
 *
 * @author David Kaluta
 * @version 21
 * @since 1
 */
public abstract class Entity {

    /**
     * The entity's x-coordinate
     */
    float x;

    /**
     * The entity's y-coordinate
     */
    float y;

    /**
     * The entity's bitmap
     */
    private Bitmap bmp;

    /**
     * Create a new entity
     *
     * @param x   X-coordinate
     * @param y   Y-coordinate
     * @param bmp Bitmap
     */
    Entity(float x, float y, Bitmap bmp) {
        this.x = x;
        this.y = y;
        this.bmp = bmp;
    }

    /**
     * Get the entity's x-coordinate
     *
     * @return the entity's x-coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Set the entity's x-coordinate
     *
     * @param x the new x-coordinate
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Get the entity's y-coordinate
     *
     * @return the entity's y-coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Set the entity's y-coordinate
     *
     * @param y the new y-coordinate
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Draw the entity
     *
     * @param c a canvas to draw the entity on
     */
    void draw(Canvas c) {
        c.drawBitmap(bmp, x, y, null);
    }
}
