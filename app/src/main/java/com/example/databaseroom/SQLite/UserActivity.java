package com.example.databaseroom.SQLite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.databaseroom.Model.DatabaseAdapter;
import com.example.databaseroom.Model.User;
import com.example.databaseroom.R;

public class UserActivity extends AppCompatActivity {

    EditText nameBox;
    EditText speedBox;
    Button delButton;
    Button saveButton;

    private DataBaseAdapter adapter;

     long carsId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameBox = findViewById(R.id.nameCar);
        speedBox = findViewById(R.id.speed);
        saveButton = findViewById(R.id.saveButton);
        delButton = findViewById(R.id.deleteButton);

        adapter = new DataBaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            carsId = extras.getLong("id");
        }
        // если 0, то добавление
        if (carsId > 0) {
            // получаем элемент по id из бд
            adapter.open();
            Cars cars = adapter.getCar(carsId);
            nameBox.setText(cars.getNameCar());
            speedBox.setText(String.valueOf(cars.getSpeedCar()));
            adapter.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }


    }

    public void delete(View view) {
        db.delete(DataBaseHelper.TABLE_CARS, "_id = ?", new String[]{String.valueOf(userId)});
        goHome();
    }


    private void goHome() {
        db.close();
        Intent intent = new Intent(this, Main_SQLite.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void saveData(View view) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(DataBaseHelper.COLUMN_CARS_NAME, nameBox.getText().toString());

            String speedValue = speedBox.getText().toString();
            if (TextUtils.isDigitsOnly(speedValue)) {
                cv.put(DataBaseHelper.COLUMN_CARS_SPEED, Integer.parseInt(speedValue));

                if (userId > 0) {
                    db.update(DataBaseHelper.TABLE_CARS, cv, DataBaseHelper.COLUMN_CARS_ID + "=" + userId, null);
                } else {
                    db.insert(DataBaseHelper.TABLE_CARS, null, cv);
                }

                goHome();
            } else {
                // Ошибка: COLUMN_CARS_SPEED не содержит числовые данные
                Log.d("But", "Invalid value for COLUMN_CARS_SPEED");
            }
        } catch (Exception e) {
            Log.d("But", String.valueOf(e));
        }
    }
}