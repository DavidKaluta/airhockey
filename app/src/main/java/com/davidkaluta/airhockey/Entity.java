package com.davidkaluta.airhockey;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Entity {

    protected float x;

    protected float y;

    protected Bitmap bmp;

    public Entity(float x, float y, Bitmap bmp) {
        this.x = x;
        this.y = y;
        this.bmp = bmp;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void draw(Canvas c) {
        c.drawBitmap(bmp, x, y, null);
    }
}
