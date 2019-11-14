package com.davidkaluta.airhockey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * The menu activity
 *
 * @author David Kaluta
 * @version 21
 * @since 1
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The difficulty selection spinner
     */
    Spinner spinner;

    /**
     * Prepare for a launch
     *
     * @param savedInstanceState required for onCreate
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] difficulties = getResources()
                .getStringArray(R.array.difficulties);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, difficulties);
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(spinnerArrayAdapter);
        final Intent msi = new Intent(this, MusicService.class);
        startService(msi);
    }

    /**
     * Launch the game
     *
     * @param view required for use in onClick
     */
    public void goToGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("DIFFICULTY", spinner.getSelectedItem().toString());
        startActivity(intent);
    }

    /**
     * Launch the credits
     *
     * @param view required for use in onClick
     */
    public void goToCredits(View view) {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    /**
     * Prepare for exit
     */
    @Override
    protected void onDestroy() {
        final Intent msi = new Intent(this, MusicService.class);
        stopService(msi);
        super.onDestroy();
    }


}
