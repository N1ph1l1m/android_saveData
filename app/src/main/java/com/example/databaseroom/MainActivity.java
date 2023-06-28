package com.example.databaseroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToShared(View view) {
        Intent intentShared = new Intent(MainActivity.this , SharedPreferences.class);
        startActivity(intentShared);
    }

    public void moveToBrain(View view) {
        Intent intentBrain = new Intent(MainActivity.this , StartBrain.class);
        startActivity(intentBrain);
    }

    public void moveSQLiteActivity(View view) {
        Intent intentSQLite = new Intent(MainActivity.this , Main_SQLite.class);
        startActivity(intentSQLite);

    }
}