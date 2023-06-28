package com.example.databaseroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class finalBrainActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private TextView textViewResult;
    private TextView textViewBestResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_brain);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewResult = findViewById(R.id.textViewResult);
        textViewBestResult = findViewById(R.id.textViewBestResult);
    }

    public void toStartBrainActivity(View view) {
        Intent intent = new Intent(finalBrainActivity.this , StartBrain.class);
        startActivity(intent);
        //startActivity(new Intent(finalBrainActivity.this, StartBrain.class));
    }
    private  void setResult(){

    }
}