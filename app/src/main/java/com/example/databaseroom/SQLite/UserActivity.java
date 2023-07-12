package com.example.databaseroom.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaseroom.MainActivity;
import com.example.databaseroom.Model.DatabaseHelper;
import com.example.databaseroom.R;

public class UserActivity extends AppCompatActivity {

    EditText nameBox;
    EditText speedBox;
    Button delButton;
    Button saveButton;

DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Cursor carCursor;

    long carsId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameBox = findViewById(R.id.nameCar);
        speedBox = findViewById(R.id.speed);
        saveButton = findViewById(R.id.saveButton);
        delButton = findViewById(R.id.deleteButton);

        dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carsId = extras.getLong("id");
        }
        if (carsId > 0) {
            // получаем элемент по id из бд
            carCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_CARS + " where " +
                    DataBaseHelper.COLUMN_CARS_ID + "=?", new String[]{String.valueOf(carsId)});
            carCursor.moveToFirst();
            nameBox.setText(carCursor.getString(1));
            speedBox.setText(String.valueOf(carCursor.getInt(2)));
            carCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }
    public void saveData(View view){
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_CARS_NAME, nameBox.getText().toString());
        cv.put(DataBaseHelper.COLUMN_CARS_SPEED, Integer.parseInt(speedBox.getText().toString()));

        if (carsId > 0) {
            db.update(DataBaseHelper.TABLE_CARS, cv, DatabaseHelper.COLUMN_ID + "=" + carsId, null);
        } else {
            db.insert(DataBaseHelper.TABLE_CARS, null, cv);
        }
        goHome();
    }
     private void goHome(){
        db.close();
        Intent intent = new Intent(this, SQLiteDatabase.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }


    public void deleteData(View view) {
        db.delete(DataBaseHelper.TABLE_CARS, "_id = ?", new String[]{String.valueOf(carsId)});
        goHome();

    }
}