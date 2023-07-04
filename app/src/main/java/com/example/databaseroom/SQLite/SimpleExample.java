package com.example.databaseroom.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.databaseroom.R;

public class SimpleExample extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_example);
        textView = findViewById(R.id.textView);
    }


    public void onClickDB(View view) {
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users(name TEXT, age INTEGER, UNIQUE(name))");
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('TOM Smith',23),('John Dow',32);");
        Cursor query = db.rawQuery("SELECT * FROM users;",null);
        textView.setText("");
        while(query.moveToNext()){
            String name = query.getString(0);
            int age = query.getInt(1);
            textView.append("Name: " + name +"\n"+ "Age:" + age +"\n");
        }
        query.close();
        db.close();
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, Main_SQLite.class);
        startActivity(intent);
        finish();
    }

}