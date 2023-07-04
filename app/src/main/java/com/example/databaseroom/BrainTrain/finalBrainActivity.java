package com.example.databaseroom.BrainTrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.databaseroom.R;

public class finalBrainActivity extends AppCompatActivity {

    private TextView textViewResult;

    private TextView answersTimer;
    private TextView textViewBestResult;

    Intent intent = getIntent();
    private  String res;
    private  float timeres;
    private float betsTimer = 10.333f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_brain);

        textViewResult = findViewById(R.id.textViewResult);
        textViewBestResult = findViewById(R.id.textViewBestResult);
        answersTimer = findViewById(R.id.textViewResultTimer);

        textViewBestResult.setText(String.valueOf(betsTimer));

       Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            res = arguments.get("result").toString();
            textViewResult.setText(res);
            timeres = (float) arguments.get("resultTime");
            if(timeres < betsTimer) {
                answersTimer.setText(String.valueOf(timeres));
                betsTimer = timeres;
                textViewBestResult.setText(String.valueOf(betsTimer));
            }else{
                answersTimer.setText(String.valueOf(timeres));
                textViewBestResult.setText(String.valueOf(betsTimer));

            }
        }

    }



    public void toStartBrainActivity(View view) {
        Intent intent = new Intent(finalBrainActivity.this , StartBrain.class);
        startActivity(intent);
        //startActivity(new Intent(finalBrainActivity.this, StartBrain.class));
    }
    private  void setResult(){

    }
}