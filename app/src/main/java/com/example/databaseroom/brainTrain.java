
package com.example.databaseroom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class brainTrain extends AppCompatActivity {

    private Button buttonTrue;

    private Button buttonFalse;
    private TextView main;
    private TextView res;
    private ActionBar actionBar;
    private SharedPreferences pref;

    private int number_1;
    private int number_2;
    private int number_false;
    private int number_index;
    private int number_res;
    private int max  = 20;
    private int min = 0;

    private int max1 = 40;
    private int min1 = 10;
    private long startTime = 0;
    private long cuttentTime = 0;
    private float time_rerult = 0.0f;
    private int true_answer = 0;
    private int max_true_answer = 10;
    private boolean is_true_answer = false;

    private final String save_key = "save_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_train);

            init();


    }

    private void init(){
        startTime = System.currentTimeMillis();

        pref = getSharedPreferences("Test" , MODE_PRIVATE);
        res = findViewById(R.id.res);
        main = findViewById(R.id.main);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Question #1");
        numbers();
        res.setText(String.valueOf(true_answer));
    }

    private void numbers(){
       number_1 = (int) (Math.random() * (max - min));
       number_2 = (int) (Math.random() * (max - min));

       number_false = (int) (Math.random() * (max1 - min1));
       number_index = (int) (Math.random() * (5 - 1));

       number_res = number_1 + number_2;
       //res.setText(String.valueOf(number_1));
        String textNum;

        if(number_index == 3 || number_index == 1 ){
            textNum = number_1 + "+" + number_2 + "=" + number_res;
            is_true_answer = true;
        }else{
            textNum = number_1 + "+" + number_2 + "=" + number_false;
            is_true_answer = false;
        }
       main.setText(textNum);
        if(true_answer>=max_true_answer){
        startActivity(new Intent(brainTrain.this, finalBrainActivity.class));
        }
    }

        public void clickTrue(View view) {

        if(is_true_answer){

            true_answer++;
            numbers();
            cuttentTime = System.currentTimeMillis();
            time_rerult = (float)(cuttentTime - startTime)/1000;
            String time = "Time:" + time_rerult;
            actionBar.setTitle(time);
        }else{
            numbers();
            cuttentTime = System.currentTimeMillis();
            time_rerult = (float)(cuttentTime - startTime)/1000;
            String time = "Time:" + time_rerult;
            actionBar.setTitle(time);
        }
        res.setText(String.valueOf(true_answer));
        }

    public void clickFalse(View view) {
        if(!is_true_answer){
            true_answer++;
            numbers();
            cuttentTime = System.currentTimeMillis();
            time_rerult = (float)(cuttentTime - startTime)/1000;
            String time = "Time:" + time_rerult;
            actionBar.setTitle(time);
        }else{
            numbers();
            cuttentTime = System.currentTimeMillis();
            time_rerult = (float)(cuttentTime - startTime)/1000;
            String time = "Time:" + time_rerult;
            actionBar.setTitle(time);
        }
        res.setText(String.valueOf(true_answer));
    }



}