package com.davidkaluta.airhockey;

import android.content.Intent;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    public static final float deviceWidth = Resources.getSystem()
    	.getDisplayMetrics().widthPixels;
    public static final float deviceHeight = Resources.getSystem()
    	.getDisplayMetrics().heightPixels;
    HockeyTable ht;
    float xDown;
    float yDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String difficulty = intent.getStringExtra("DIFFICULTY");
        ht = new HockeyTable(this, difficulty);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(ht);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action = event.getActionMasked();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                xDown = event.getX();
                yDown = event.getY();
                ht.getRP().setX(xDown);
                ht.getRP().setY(yDown > deviceHeight / 2 ?
                 yDown : deviceHeight/2);
                return true;
            case MotionEvent.ACTION_MOVE:
                float xMove = event.getX();
                float yMove = event.getY();
                ht.getRP().setX(xMove);
                ht.getRP().setY(yMove > deviceHeight / 2 ?
                 yMove : deviceHeight/2);
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
