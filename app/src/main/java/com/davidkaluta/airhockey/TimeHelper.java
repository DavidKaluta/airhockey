package com.davidkaluta.airhockey;

import java.util.TimerTask;

/**
 * A time helper for counting down seconds
 *
 * @author David Kaluta
 * @version 24
 * @since 24
 */
public class TimeHelper extends TimerTask {

    /**
     * The seconds counter for the timer
     */
    static int seconds;

    /**
     * Create a new timer
     */
    TimeHelper() {
        TimeHelper.seconds = 0;
    }

    /**
     * The timer action
     */
    @Override
    public void run() {
        TimeHelper.seconds++;
    }

}
