package com.davidkaluta.airhockey;

import android.content.Intent;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * The activity the game runs in
 *
 * @author David Kaluta
 * @version 21
 * @since 1
 */
public class GameActivity extends AppCompatActivity {

    /**
     * The width of the device
     */
    public static final float deviceWidth = Resources.getSystem()
            .getDisplayMetrics().widthPixels;

    /**
     * The height of the device
     */
    public static final float deviceHeight = Resources.getSystem()
            .getDisplayMetrics().heightPixels;

    /**
     * A HockeyTable for the game
     */
    HockeyTable ht;

    /**
     * X-coordinate for touch location
     */
    float xDown;

    /**
     * Y-coordinate for touch location
     */
    float yDown;

    String difficulty;

    /**
     * Prepare for opening game
     *
     * @param savedInstanceState required for onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        difficulty = intent.getStringExtra("DIFFICULTY");
        if (difficulty != null)
            ht = new HockeyTable(this, difficulty);
        else
            ht = new HockeyTable(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility
                (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(ht);
    }

    /**
     * Make the app full screen
     *
     * @param hasFocus is the app in focus
     */
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


    /**
     * Check if the screen is touched
     *
     * @param event a MotionEvent with a touch action
     * @return true if the screen has been touched
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {


        int action = event.getActionMasked();

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                xDown = event.getX();
                yDown = event.getY();
                if(ht.getRP().delay == 0) {
                    if (yDown > deviceHeight / 2) {
                        ht.getRP().setY(yDown);
                        ht.getRP().setX(xDown);
                    }
                    else if (ht.getBP().getV() == 0) {
                        ht.getBP().setY(yDown);
                        ht.getBP().setX(xDown);
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                float xMove = event.getX();
                float yMove = event.getY();
                if(ht.getRP().delay == 0) {
                    if(yDown > deviceHeight / 2) {
                        ht.getRP().setX(xMove);
                        ht.getRP().setY(yMove);
                    }
                    else if (ht.getBP().getV() == 0) {
                        ht.getBP().setY(yMove);
                        ht.getBP().setX(xMove);
                    }
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
