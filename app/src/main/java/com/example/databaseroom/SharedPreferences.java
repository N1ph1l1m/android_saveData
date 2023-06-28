package com.example.databaseroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferences extends AppCompatActivity {


    private android.content.SharedPreferences pref;
    private EditText editSave;
    private final String setKey = "save_key";
    private TextView getText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences);
        init();
        getText = findViewById(R.id.getTextView);
    }

    public void save(View view) {
        android.content.SharedPreferences.Editor edit = pref.edit();
        edit.putString(setKey, editSave.getText().toString());
        edit.apply();
    }

    public void get(View view) {
        getText.setText(pref.getString(setKey,"Text"));
    }

    private void init(){
        pref = getSharedPreferences("Test" , MODE_PRIVATE);
        editSave = findViewById(R.id.editText);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}