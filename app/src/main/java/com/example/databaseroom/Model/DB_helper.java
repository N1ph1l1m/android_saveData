package com.example.databaseroom.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userstore.db";
    private static final int SCHEMA = 1;

    static final String TABLE = "users";

    public static final String COLUME_ID = "_id";
    public static final String COLUME_NAME = "name";
    public static final String COLUME_YEAR = "year";



    public DB_helper(Context context) {
        super(context, DATABASE_NAME, null ,SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE  TABLE " + TABLE + "(" + COLUME_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUME_NAME + " TEXT," + COLUME_YEAR + " INTEGER);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }
}
