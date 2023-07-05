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

import com.example.databaseroom.R;

public class UserActivity extends AppCompatActivity {

    EditText nameBox;
    EditText yearBox;
    Button delButton;
    Button saveButton;

    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameBox = findViewById(R.id.name);
        yearBox = findViewById(R.id.year);
        saveButton = findViewById(R.id.saveButton);
        delButton = findViewById(R.id.deleteButton);

        sqlHelper = new DataBaseHelper(this);
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            userId = extras.getLong("id");
        }

        if(userId > 0 ){
            userCursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_CARS + " WHERE " + DataBaseHelper.COLUMN_CARS_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            nameBox.setText(userCursor.getString(1));
            yearBox.setText(String.valueOf(userCursor.getInt(2)));
            userCursor.close();
        }else{
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view) {
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_CARS_NAME,nameBox.getText().toString());
        cv.put(DataBaseHelper.COLUMN_CARS_SPEED,Integer.parseInt(yearBox.getText().toString()));

        if(userId > 0){
            db.update(DataBaseHelper.TABLE_CARS, cv, DataBaseHelper.COLUMN_CARS_ID + "=" + userId , null);
        }else{
            db.insert(DataBaseHelper.TABLE_CARS, null , cv);
        }
        goHome();

    }

    public void delete(View view) {
        db.delete(DataBaseHelper.TABLE_CARS, "id = ?", new String[]{String.valueOf(userId)});
        goHome();
    }


    private void goHome() {
        db.close();
        Intent intent = new Intent(this, Main_SQLite.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}