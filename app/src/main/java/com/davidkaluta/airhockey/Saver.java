package com.davidkaluta.airhockey;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Saving code
 *
 * @author David Kaluta
 * @version 22
 * @since 8
 */
class Saver {
    static int getWins(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        int defaultValue = 0;
        return sharedPref.getInt(context.getString(
                R.string.victories_key),
                defaultValue);
    }

    static int getWins(Context context, String difficulty) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        int defaultValue = 0;
        switch (difficulty) {
            case "Easy":
                return sharedPref.getInt(context.getString(
                        R.string.victories_key_easy),
                        defaultValue);
            case "Medium":
                return sharedPref.getInt(context.getString(
                        R.string.victories_key_medium),
                        defaultValue);
            case "Hard":
                return sharedPref.getInt(context.getString(
                        R.string.victories_key_hard),
                        defaultValue);
            case "2 Players":
                return sharedPref.getInt(context.getString(
                        R.string.victories_key_2players),
                        defaultValue);
            default:
                return sharedPref.getInt(context.getString(
                        R.string.victories_key),
                        defaultValue);
        }
    }

    static void setWins(Context context, int w) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(context.getString(R.string.victories_key), w);
        editor.apply();
    }
    static void setWins(Context context, int w, String difficulty) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preferences_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        switch(difficulty) {
            case "Easy":
                editor.putInt(context.getString(R.string.victories_key_easy), w);
                break;
            case "Medium":
                editor.putInt(context.getString(R.string.victories_key_medium),w);
                break;
            case "Hard":
                editor.putInt(context.getString(R.string.victories_key_hard), w);
                break;
            case "2 Players":
                editor.putInt(context.getString(R.string.victories_key_2players),w);
                break;
        }
    }
}
