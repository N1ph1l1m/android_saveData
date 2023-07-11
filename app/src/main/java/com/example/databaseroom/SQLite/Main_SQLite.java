package com.example.databaseroom.SQLite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.databaseroom.MainActivity;
import com.example.databaseroom.R;
import com.example.databaseroom.SQLite.DataBaseHelper;
import com.example.databaseroom.SQLite.SimpleExample;
import com.example.databaseroom.SQLite.UserActivity;

import java.util.List;

public class Main_SQLite extends AppCompatActivity {

    ListView userList;
    ArrayAdapter<Cars> arrayAdapter;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqlite);

        userList = findViewById(R.id.list);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        dataBaseHelper.create_db();
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Cars cars = arrayAdapter.getItem(position);
                if(cars != null){
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        DataBaseAdapter adapter = new DataBaseAdapter(this);
        adapter.open();

        List<Cars> cars = adapter.getUsers();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,cars);
        userList.setAdapter(arrayAdapter);
        adapter.close();


    }

    public void addData(View view) {
       Intent intent = new Intent(this , UserActivity.class);
       startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        userCursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.dataBase1){
            startActivity(new Intent(this, SimpleExample.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}