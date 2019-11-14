package com.davidkaluta.airhockey;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * A class for saving scores
 *
 * @author David Kaluta
 * @version 21
 * @since 7
 */
class Saver {

    /**
     * @param context Activity to get prefs from
     * @return number of wins
     */
    static int getWins(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        int defaultValue = 0;
        sharedPref.getInt(context.getString(
                R.string.victories_key),
                defaultValue);
        return sharedPref.getInt(context.getString(
                R.string.victories_key),
                defaultValue);
    }

    /**
     * Set a new number of wins
     *
     * @param context Activity to get prefs from
     * @param w       new number of wins
     */
    static void setWins(Context context, int w) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(context.getString(R.string.victories_key), w);
        editor.apply();
    }


}
