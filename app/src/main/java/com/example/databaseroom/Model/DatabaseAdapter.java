package com.example.databaseroom.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DB_helper dataBaseHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dataBaseHelper = new DB_helper(context.getApplicationContext());
    }

    public  DatabaseAdapter open (){
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dataBaseHelper.close();
    }

    public Cursor getAllEntries(){
        String[] columns = new String[]{DB_helper.COLUMN_ID,DB_helper.COLUMN_NAME,DB_helper.COLUMN_YEAR};
        return  database.query(DB_helper.TABLE, columns,
                null,
                null,
                null,
                null,
                null);
    }
     public List<User>getUsers(){
         ArrayList<User> users = new ArrayList<>();
         Cursor cursor = getAllEntries();
         while(cursor.moveToNext()){
             int id = cursor.getInt(cursor.getColumnIndex(DB_helper.COLUMN_ID));
             String name = cursor.getString(cursor.getColumnIndex(DB_helper.COLUMN_NAME));
             int year = cursor.getInt(cursor.getColumnIndex(DB_helper.COLUMN_YEAR));
            users.add(new User(id,name,year));
         }
         cursor.close();
         return users;
     }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DB_helper.TABLE);
    }


    public User getUser(long id){
        User user = null;
        String query = String.format(" SELECT * FROM %s WHERE %s=?" , DB_helper.TABLE, DB_helper.COLUMN_ID );
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(DB_helper.COLUMN_NAME));
            int year = cursor.getInt(cursor.getColumnIndex(DB_helper.COLUMN_YEAR));
            user = new User(id,name,year);
        }
        cursor.close();
        return user;
     }

     public  long insert(User user){
         ContentValues cv = new ContentValues();
         cv.put(DB_helper.COLUMN_NAME,user.getName());
         cv.put(DB_helper.COLUMN_YEAR,user.getYear());
         return database.insert(DB_helper.TABLE, null , cv);
     }
     public long delete(long userId){
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return  database.delete(DB_helper.TABLE, whereClause,whereArgs);
     }
     public long update(User user){
        String whereClause = DB_helper.COLUMN_ID + "=" + user.getId();
        ContentValues cv = new ContentValues();
        cv.put(DB_helper.COLUMN_NAME, user.getName());
        cv.put(DB_helper.COLUMN_YEAR, user.getYear());
        return database.update(DB_helper.TABLE , cv , whereClause , null);
     }


}
