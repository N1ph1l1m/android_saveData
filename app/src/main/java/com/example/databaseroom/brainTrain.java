
package com.example.databaseroom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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
    private int number_res;
    private int max  = 20;
    private int min = 0;

    private int max1 = 40;
    private int min1 = 10;
    private final String save_key = "save_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_train);

            init();


    }

    private void init(){
        pref = getSharedPreferences("Test" , MODE_PRIVATE);
        res = findViewById(R.id.res);
        main = findViewById(R.id.main);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Question #1");
    }

    private void numbers(){
       number_1 = (int) (Math.random() * (max - min));
       number_2 = (int) (Math.random() * (max - min));
       number_false = (int) (Math.random() * (max1 - min1));
       number_res = number_1 + number_2;
       res.setText(String.valueOf(number_1));
       main.setText(String.valueOf(number_2) );

    }

    public void clickTrue(View view) {
        numbers();
    }

    public void clickFalse(View view) {
    }


}