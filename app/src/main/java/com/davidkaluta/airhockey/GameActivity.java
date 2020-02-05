package com.davidkaluta.airhockey;

import android.content.Intent;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

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

    float xDown1;

    float yDown1;

    float xDown2;

    float yDown2;

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
            case MotionEvent.ACTION_DOWN:
                xDown1 = event.getX();
                yDown1 = event.getY();
                if(xDown1 > ht.getRP().x - 64 && xDown1 < ht.getRP().x + ht.getRP().radius + 64) {
                    if (yDown1 > ht.getRP().y - 64 && yDown1 < ht.getRP().y + ht.getRP().radius + 64) {
                        ht.getRP().setX(xDown1);
                        ht.getRP().setY(yDown1 > deviceHeight / 2 + ht.getRP().radius ? yDown1 : deviceHeight / 2 + ht.getRP().radius);
                    }
                }
                else if (difficulty.equals("2 Players") && xDown1 > ht.getBP().x - 64 && xDown1 < ht.getBP().x + ht.getBP().radius + 64) {
                    if (yDown1 > ht.getBP().y - 64 && yDown1 < ht.getBP().y + ht.getBP().radius + 64) {
                        ht.getBP().setX(xDown1);
                        ht.getBP().setY(yDown1 < deviceHeight / 2 - ht.getRP().radius ? yDown1 : deviceHeight / 2 - ht.getRP().radius);
                    }
                }
                return true;
            case MotionEvent.ACTION_POINTER_DOWN:
                xDown2 = event.getX(1);
                yDown2 = event.getY(1);
                if(xDown2 > ht.getRP().x - 64 && xDown2 < ht.getRP().x + ht.getRP().radius + 64) {
                    if (yDown2 > ht.getRP().y - 64 && yDown2 < ht.getRP().y + ht.getRP().radius + 64) {
                        ht.getRP().setX(xDown2);
                        ht.getRP().setY(yDown2 > deviceHeight / 2 + ht.getRP().radius ? yDown2 : deviceHeight / 2 + ht.getRP().radius);
                    }
                }
                else if (difficulty.equals("2 Players") && xDown2 > ht.getBP().x - 64 && xDown2 < ht.getBP().x + ht.getBP().radius + 64) {
                    if (yDown2 > ht.getBP().y - 64 && yDown2 < ht.getBP().y + ht.getBP().radius + 64) {
                        ht.getBP().setX(xDown2);
                        ht.getBP().setY(yDown2 < deviceHeight / 2 - ht.getRP().radius ? yDown2 : deviceHeight / 2 - ht.getRP().radius);
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(event.getPointerCount() == 2) {
                    for (int i = 0; i < 2; i++) {
                        float xMove = event.getX(i);
                        float yMove = event.getY(i);
                        if (xMove > ht.getRP().x - 64 && xMove < ht.getRP().x + ht.getRP().radius + 64) {
                            if (yMove > ht.getRP().y - 64 && yMove < ht.getRP().y + ht.getRP().radius + 64) {
                                ht.getRP().setX(xMove);
                                ht.getRP().setY(yMove > deviceHeight / 2 + ht.getRP().radius ? yMove : deviceHeight / 2 + ht.getRP().radius);
                            }
                        } else if (xMove > ht.getBP().x - 64 && xMove < ht.getBP().x + ht.getBP().radius + 64) {
                            if (yMove > ht.getBP().y - 64 && yMove < ht.getBP().y + ht.getBP().radius + 64) {
                                ht.getBP().setX(xMove);
                                ht.getBP().setY(yMove < deviceHeight / 2 - ht.getRP().radius ? yMove : deviceHeight / 2 - ht.getRP().radius);
                            }
                        }
                    }
                }
                else {
                    float xMove = event.getX();
                    float yMove = event.getY();
                    if(xMove > ht.getRP().x - 64 && xMove < ht.getRP().x + ht.getRP().radius + 64) {
                        if (yMove > ht.getRP().y - 64 && yMove < ht.getRP().y + ht.getRP().radius + 64) {
                            ht.getRP().setX(xMove);
                            ht.getRP().setY(yMove > deviceHeight / 2 + ht.getRP().radius ? yMove : deviceHeight / 2 + ht.getRP().radius);
                        }
                    }
                    else if (difficulty.equals("2 Players") && xMove > ht.getBP().x - 64 && xMove < ht.getBP().x + ht.getBP().radius + 64) {
                        if (yMove > ht.getBP().y - 64 && yMove < ht.getBP().y + ht.getBP().radius + 64) {
                            ht.getBP().setX(xMove);
                            ht.getBP().setY(yMove < deviceHeight / 2 - ht.getRP().radius ? yMove : deviceHeight / 2 - ht.getRP().radius);
                        }
                    }
                }
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }
}
