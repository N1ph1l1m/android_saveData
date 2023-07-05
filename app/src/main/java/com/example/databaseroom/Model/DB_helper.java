package com.example.databaseroom.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public void addUser(User user){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Util.COLUMN_NAME,user.getName());
        cv.put(Util.COLUMN_YEAR,user.getYear());

        db.insert(Util.TABLE_NAME,null,cv);
        db.close();
    }
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.COLUMN_NAME, Util.COLUMN_YEAR},Util.COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        User user = new User(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getInt(2));
        return user;
    }
}
