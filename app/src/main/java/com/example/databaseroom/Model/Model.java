package com.example.databaseroom.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.databaseroom.MainActivity;
import com.example.databaseroom.R;
import com.example.databaseroom.SQLite.DataBaseHelper;
import com.example.databaseroom.SQLite.UserActivity;

import java.util.List;

public class Model extends AppCompatActivity {

    private ListView userList;
    ArrayAdapter<User> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);


        userList = findViewById(R.id.list2);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = arrayAdapter.getItem(position);
                if (user != null) {
                    Intent intent = new Intent(getApplicationContext(), user_activity2.class);
                    intent.putExtra("id", user.getId());
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<User> users = adapter.getUsers();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        userList.setAdapter(arrayAdapter);
        adapter.close();
    }

    // по нажатию на кнопку запускаем UserActivity для добавления данных
    public void add(View view){
        Intent intent = new Intent(this, user_activity2.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}