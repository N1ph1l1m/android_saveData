package com.example.databaseroom.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.databaseroom.MainActivity;
import com.example.databaseroom.R;
import com.example.databaseroom.SQLite.DataBaseHelper;

import java.util.List;

public class Model extends AppCompatActivity {

    ListView userList;
    SimpleCursorAdapter userAdapter;
    SQLiteDatabase db;
    Cursor userCursor;
    //User user;
    //Cursor cursor;
    DB_helper dataBaseHelper;
    long userId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);
//        textView = findViewById(R.id.nameView);
        userList = findViewById(R.id.list2);
//        DB_helper database = new DB_helper(this);
//        database.addUser(new User("Bruce Waine", 35));
//        database.addUser(new User("Bruce Waine", 36));
//        database.addUser(new User("Bruce Waine", 37));
//        database.addUser(new User("Bruce Waine", 38));
//        Bundle extras = getIntent().getExtras();
//        if(extras != null){
//            userId = extras.getLong("id");
//        }
//
////    try {
//        if(userId>0){
//            String name = String.valueOf(database.getUser(1));
//            textView.setText(name);
//            database.close();
//        }
////    }catch (Exception e){
////        Log.d("ErData", String.valueOf(e));
////    }
        dataBaseHelper = new DB_helper(getApplicationContext());
        dataBaseHelper.createDB();



    }

    @Override
    protected void onResume() {
        try{
            super.onResume();
            db = dataBaseHelper.open();

            userCursor = db.rawQuery(" SELECT * FROM  " + Util.TABLE_NAME , null);

            String[] headers = new String[] {Util.COLUMN_NAME, Util.COLUMN_YEAR};

            userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                    userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2},0);
            userList.setAdapter(userAdapter);
        }catch (Exception e){
            Log.d("ErrU", String.valueOf(e));
        }
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}