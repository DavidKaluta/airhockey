package com.davidkaluta.airhockey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        TextView timesWon =  (TextView) findViewById(R.id.timesWon);
        timesWon.setText(getString(R.string.times_won, Saver.getWins(this)));
    }
}
