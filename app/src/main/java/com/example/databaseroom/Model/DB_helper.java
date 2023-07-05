package com.example.databaseroom.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_helper extends SQLiteOpenHelper {




    public DB_helper(Context context) {
        super(context, Util.DATABASE_NAME, null ,Util.SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = " CREATE  TABLE " + Util.TABLE_NAME + "(" + Util.COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Util.COLUMN_NAME + " TEXT," + Util.COLUMN_YEAR + " INTEGER);" ;
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
