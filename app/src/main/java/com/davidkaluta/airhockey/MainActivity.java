package com.davidkaluta.airhockey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] difficulties = getResources()
        	.getStringArray(R.array.difficulties);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, difficulties );
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(spinnerArrayAdapter);
        final Intent msi = new Intent(this, MusicService.class);
        startService(msi);
    }

    public void goToGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("DIFFICULTY", spinner.getSelectedItem().toString());
        startActivity(intent);
    }

    public void goToCredits(View view) {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        final Intent msi = new Intent(this, MusicService.class);
        stopService(msi);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        final Intent msi = new Intent(this, MusicService.class);
        stopService(msi);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Intent msi = new Intent(this, MusicService.class);
        startService(msi);
    }


}
