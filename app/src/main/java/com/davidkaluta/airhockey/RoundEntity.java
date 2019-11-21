package com.davidkaluta.airhockey;

import android.graphics.Bitmap;

/**
 * A round entity subclass
 *
 * @author David Kaluta
 * @version 21
 * @since 1
 */
public abstract class RoundEntity extends Entity {

    /**
     * The center point's x-coordinate
     */
    float centerPointX;

    /**
     * The center point's y-coordinate
     */
    float centerPointY;

    /**
     * The round entity's radius
     */
    float radius;

    /**
     * Initialize a new round entity
     *
     * @param x   The entity's x-coordinate
     * @param y   The entity's x-coordinate
     * @param bmp A Bitmap
     */
    RoundEntity(float x, float y, Bitmap bmp) {
        super(x - (float) bmp.getWidth() / 2, y - (float) bmp.getHeight() / 2,
         bmp);
        radius = bmp.getWidth() / 2;
        centerPointX = x + radius;
        x -= radius;
        centerPointY = y + radius;
        y -= radius;
    }

    /**
     * Get the center point x-coordinate
     *
     * @return the center point x-coordinate
     */
    float getCenterPointX() {
        return centerPointX;
    }


    /**
     * Get the center point y-coordinate
     *
     * @return the center point y-coordinate
     */
    public float getCenterPointY() {
        return centerPointY;
    }


    /**
     * Get the entity radius
     *
     * @return the entity's radius
     */
    public float getRadius() {
        return radius;
    }


    /**
     * Set the x-coordinate with changing the center point
     *
     * @param x the new x-coordinate
     */
    @Override
    public void setX(float x) {
        super.setX(x - radius);
        centerPointX = x + radius;
    }

    /**
     * Set the y-coordinate with changing the center point
     *
     * @param y the new y-coordinate
     */
    @Override
    public void setY(float y) {
        super.setY(y - radius);
        centerPointY = y + radius;
    }

    /**
     * Get the distance between 2 round entities
     *
     * @param other another RoundEntity
     * @return the distance between them
     */
    float distanceFrom(RoundEntity other) {
        return (float) Math.sqrt(
                (centerPointX - other.centerPointX) *
                        (centerPointX - other.centerPointX)
                        + (centerPointY - other.centerPointY) *
                        (centerPointY - other.centerPointY));
    }

}
