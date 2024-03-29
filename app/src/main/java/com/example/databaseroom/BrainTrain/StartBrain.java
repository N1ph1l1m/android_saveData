package com.example.databaseroom.BrainTrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.databaseroom.MainActivity;
import com.example.databaseroom.R;


public class StartBrain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_brain);
    }

    public void nextActivity(View view) {
        startActivity(new Intent(StartBrain.this, brainTrain.class));
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}