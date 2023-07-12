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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.databaseroom.MainActivity;
import com.example.databaseroom.R;

public class Main_SQLite extends AppCompatActivity {

    ListView carList;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Cursor carCursor;
    SimpleCursorAdapter carAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqlite);

        carList = findViewById(R.id.list);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        dataBaseHelper.create_db();
        carList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                    startActivity(intent);


            }
        });
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = dataBaseHelper.open();
        //получаем данные из бд в виде курсора
        carCursor = db.rawQuery(" SELECT * FROM " + DataBaseHelper.TABLE_CARS, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        String[] headers = new String[]{DataBaseHelper.COLUMN_CARS_NAME, DataBaseHelper.COLUMN_CARS_SPEED};
        // создаем адаптер, передаем в него курсор
        carAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                carCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        carList.setAdapter(carAdapter);
    }

    public void addData(View view) {
       Intent intent = new Intent(this , UserActivity.class);
       startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        carCursor.close();
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