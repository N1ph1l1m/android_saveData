package com.example.databaseroom.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private  static final String  DATABASE_NAME = "userStore.db";
    private  static final int SCHEME =1;
    public static final  String TABLE = "users";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_YEAR = "year";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE users("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT," + COLUMN_YEAR + " INTEGER);" );


//        sqLiteDatabase.execSQL("INSERT INTO " + TABLE + " (" + COLUMN_NAME + "," + COLUMN_YEAR + ") VALUES ('Tom Smith' , 1981);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);

    }
}
