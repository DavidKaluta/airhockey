package com.davidkaluta.airhockey;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import static com.davidkaluta.airhockey.GameActivity.deviceHeight;
import static com.davidkaluta.airhockey.GameActivity.deviceWidth;


public class HockeyTable extends View {

    RedPaddle rp;
    BluePaddle bp;
    Puck p;
    Bitmap bg;
    Bitmap line;
    Paint paint;

    public HockeyTable(Context context, String difficulty) {
        super(context);
        paint = new Paint();
        int deviceWidth = Resources.getSystem().getDisplayMetrics()
                .widthPixels;
        int deviceHeight = Resources.getSystem().getDisplayMetrics()
                .heightPixels;
        bg = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.black_pixel)
                , deviceWidth, deviceHeight, true);
        line = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(
                        getResources(), R.drawable.black_pixel)
                , deviceWidth, 20, true);
        rp = new RedPaddle(deviceWidth/2,7*deviceHeight/8,
        new Goal(deviceWidth/4, 0, this),this);
        switch(difficulty) {
            case "Easy":
                bp = new BluePaddle(deviceWidth/2, 1 * deviceHeight/8,0.5,
                new Goal(deviceWidth/4, deviceHeight-10, this), this);
                break;
            case "Medium":
            default:
                bp = new BluePaddle(deviceWidth/2, 1 * deviceHeight/8,1,
                new Goal(deviceWidth/4, deviceHeight-10, this), this);
                break;
            case "Hard":
                bp = new BluePaddle(deviceWidth/2, 1 * deviceHeight/8,2,
                new Goal(deviceWidth/4, deviceHeight-10, this), this);
                break;
            case "BRUTAL":
                bp = new BluePaddle(deviceWidth/2, 1 * deviceHeight/8,123,
                new Goal(deviceWidth/4, deviceHeight-10, this), this);
                break;
        }
        p = new Puck(deviceWidth/2, deviceHeight/2, this);
    }

    public RedPaddle getRP() {
        return rp;
    }

    public Puck getP() {
        return p;
    }

    public BluePaddle getBP() {
        return bp;
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);
        c.drawBitmap(bg, 0, 0, null);
        c.drawBitmap(line, 0,deviceHeight/2 - 10, null);
        rp.draw(c);
        rp.getGoal().draw(c);
        bp.getGoal().draw(c);
        bp.draw(c);
        p.draw(c);

        paint.setTextSize(144);
        paint.setColor(Color.WHITE);
        if(rp.isWinner())
            c.drawText(getContext().getString(R.string.red_victory),
             200, deviceHeight / 2 - 100, paint);
        if(bp.isWinner())
            c.drawText(getContext().getString(R.string.blue_victory),
             200, deviceHeight / 2 - 100, paint);
        paint.setTextSize(50);
        c.drawText(Integer.toString(rp.getGoal().getScore()),
         10, deviceHeight - 100, paint);
        c.drawText(Integer.toString(bp.getGoal().getScore()),
         deviceWidth - 100, 50, paint);
        invalidate();
    }
}
