package com.davidkaluta.airhockey;

import android.graphics.Bitmap;

public class RoundEntity extends Entity {

    float centerPointX;
    float centerPointY;
    float radius;

    public RoundEntity(float x, float y, Bitmap bmp) {
        super(x-bmp.getWidth()/2, y-bmp.getHeight()/2, bmp);
        radius = bmp.getWidth()/2;
        centerPointX = x + radius;
        x-=radius;
        centerPointY = y + radius;
        y-=radius;
    }

    public float getCenterPointX() {
        return centerPointX;
    }


    public float getCenterPointY() {
        return centerPointY;
    }


    public float getRadius() {
        return radius;
    }


    @Override
    public void setX(float x) {
        super.setX(x - radius);
        centerPointX = x + radius;
    }

    @Override
    public void setY(float y) {
        super.setY(y - radius);
        centerPointY = y + radius;
    }

    public float distanceFrom(RoundEntity other) {
        return (float) Math.sqrt(
                (centerPointX - other.centerPointX)*
                (centerPointX - other.centerPointX)
                +(centerPointY - other.centerPointY)*
                (centerPointY - other.centerPointY));
    }

}
