package com.davidkaluta.airhockey;

import java.util.TimerTask;

/**
 * A time helper for counting down seconds
 * @author David Kaluta
 * @version 24
 * @since 24
 */
public class TimeHelper extends TimerTask {

    public static int seconds;

    public TimeHelper() {
        TimeHelper.seconds = 0;
    }

    @Override
    public void run() {
        TimeHelper.seconds++;
    }

}
