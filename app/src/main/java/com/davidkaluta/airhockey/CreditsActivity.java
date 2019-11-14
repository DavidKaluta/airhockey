package com.davidkaluta.airhockey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * The credits screen
 *
 * @author David Kaluta
 * @version 21
 * @since 7
 */
public class CreditsActivity extends AppCompatActivity {

    /**
     * Prepare for creation
     *
     * @param savedInstanceState required for Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        TextView timesWon = findViewById(R.id.timesWon);
        timesWon.setText(getString(R.string.times_won, Saver.getWins(this)));
    }
}
