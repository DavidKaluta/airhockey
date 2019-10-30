package com.davidkaluta.airhockey;

import android.content.Context;
import android.content.SharedPreferences;

public class Saver {
    public static int getWins(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        int defaultValue = 0;
        int wins = sharedPref.getInt(context.getString(
                R.string.victories_key),
                defaultValue);
        return wins;
    }

    public static void setWins(Context context, int w) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(context.getString(R.string.victories_key), w);
        editor.commit();
    }


}
