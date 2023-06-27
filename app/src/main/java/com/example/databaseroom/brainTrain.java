
package com.example.databaseroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;

public class brainTrain extends AppCompatActivity {

    private Button buttonSave;
    private Button buttonGet;
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
    }
}