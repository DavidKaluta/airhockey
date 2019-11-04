package com.davidkaluta.airhockey;

import android.content.Context;
import android.content.SharedPreferences;

class Saver {
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

    static void setWins(Context context, int w) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(context.getString(R.string.victories_key), w);
        editor.apply();
    }


}
