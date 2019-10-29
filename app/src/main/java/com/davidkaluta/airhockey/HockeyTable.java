package com.davidkaluta.airhockey;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.View;

public class HockeyTable extends View {

    RedPaddle rp;
    Puck p;

    public HockeyTable(Context context) {
        super(context);
        int deviceWidth = Resources.getSystem().getDisplayMetrics()
                .widthPixels;
        int deviceHeight = Resources.getSystem().getDisplayMetrics()
                .heightPixels;
        rp = new RedPaddle(deviceWidth/2,7*deviceHeight/8, this);
        p = new Puck(deviceWidth/2, deviceHeight/2, this);
    }

    public RedPaddle getRP() {
        return rp;
    }

    protected void onDraw(Canvas c) {
        super.onDraw(c);
        rp.draw(c);
        p.draw(c);
        invalidate();
    }
}
