package com.example.databaseroom.SQLite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.databaseroom.Model.DatabaseHelper;
import com.example.databaseroom.Model.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseAdapter {

    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public DataBaseAdapter(Context context){
        dataBaseHelper = new DataBaseHelper(context.getApplicationContext());

    }

    public DataBaseAdapter open(){
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dataBaseHelper.close();
    }
    private Cursor getAllEntries(){
        String[] columns = new String[]{DataBaseHelper.COLUMN_CARS_ID,DataBaseHelper.COLUMN_CARS_NAME,DataBaseHelper.COLUMN_CARS_SPEED};
        return database.query(DataBaseHelper.TABLE_CARS,columns,null,null,null,null,null);
    }

    public List<Cars> getCars(){
        ArrayList<Cars> cars = new ArrayList<>();
        Cursor cursor = getAllEntries();
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_CARS_ID));
            @SuppressLint("Range") int String = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_CARS_NAME));
            @SuppressLint("Range") int speed = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_CARS_SPEED));
        }
        cursor.close();
        return cars;
    }

    public Cars getCar(long id){
        Cars  cars = null;
        String query = String.format("SELECT * FROM %s WHERE %s=?",DataBaseHelper.TABLE_CARS, DataBaseHelper.COLUMN_CARS_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_CARS_NAME));
            @SuppressLint("Range") int speed = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_CARS_SPEED));
            cars = new Cars(id, name ,speed);
        }
        cursor.close();
        return  cars;
    }

    public long insert(Cars cars){

        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_CARS_NAME, cars.getNameCar());
        cv.put(DataBaseHelper.COLUMN_CARS_SPEED, cars.getSpeedCar());

        return  database.insert(DataBaseHelper.TABLE_CARS, null, cv);
    }
    public long delete(long userId){

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{String.valueOf(userId)};
        return database.delete(DataBaseHelper.TABLE_CARS, whereClause, whereArgs);
    }
    public long update(Cars cars){

        String whereClause = DataBaseHelper.COLUMN_CARS_ID + "=" + cars.getId();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_CARS_NAME,cars.getNameCar());
        cv.put(DataBaseHelper.COLUMN_CARS_SPEED,cars.getSpeedCar());
        return database.update(DataBaseHelper.TABLE_CARS, cv, whereClause, null);
    }
}
