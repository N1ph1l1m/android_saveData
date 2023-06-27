
package com.example.databaseroom;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class brainTrain extends AppCompatActivity {

    private Button buttonTrue;
    private Button buttonFalse;
    private TextView question;
    private TextView timer;
    private ActionBar actionBar;
    private SharedPreferences pref;
    private final String save_key = "save_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_train);

            init();


    }

    private void init(){
        pref = getSharedPreferences("Test" , MODE_PRIVATE);
        timer = findViewById(R.id.timer);
        question = findViewById(R.id.textViewQuestion);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Question #1");
    }

    public void clickTrue(View view) {
    }

    public void clickFalse(View view) {
    }


}