package com.example.databaseroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setContentView(R.layout.activity_main);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.brainActivity){
            startActivity(new Intent(SharedPreferences.this, StartBrain.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}