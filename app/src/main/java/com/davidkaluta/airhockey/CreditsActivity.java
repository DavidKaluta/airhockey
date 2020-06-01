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
        TextView timesWonEasy = findViewById(R.id.timesWonEasy);
        timesWonEasy.setText(getString(R.string.times_won_easy, Saver.getWins(this, "Easy")));
        TextView timesWonMedium = findViewById(R.id.timesWonMedium);
        timesWonMedium.setText(getString(R.string.times_won_medium, Saver.getWins(this, "Medium")));
        TextView timesWonHard = findViewById(R.id.timesWonHard);
        timesWonHard.setText(getString(R.string.times_won_hard, Saver.getWins(this, "Hard")));
        TextView timesWon2Player = findViewById(R.id.timesWon2Player);
        timesWon2Player.setText(getString(R.string.times_won_2player, Saver.getWins(this, "2 Players")));
    }
}
