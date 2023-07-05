package com.example.databaseroom.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class DB_helper extends SQLiteOpenHelper {
    private Context context;

    public DB_helper(Context context) {
        super(context, Util.DATABASE_NAME, null ,Util.SCHEMA);
        this.context = context;
        Util.DB_PATH_User = context.getFilesDir().getPath() + Util.DB_PATH_User;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    public void createDB(){
        File file = new File(Util.DB_PATH_User);
        if (!file.exists()) {
            //получаем локальную бд как поток
            Context context = null;
            try(InputStream myInput = context.getAssets().open(Util.TABLE_NAME);
                // Открываем пустую бд
                OutputStream myOutput = Files.newOutputStream(Paths.get(Util.DB_PATH_User))) {

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }
                myOutput.flush();
            }
            catch(IOException ex){
                Log.d("DatabaseHelper", ex.getMessage());
            }
        }
    }
    public SQLiteDatabase open()throws SQLException {

        return SQLiteDatabase.openDatabase(Util.DB_PATH_User, null, SQLiteDatabase.OPEN_READWRITE);
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

        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.COLUMN_NAME},Util.COLUMN_ID + "=?", new String[]{String.valueOf(id)},
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

    public  List<User> getAllUser(){
        SQLiteDatabase db = getReadableDatabase();
        List<User> userList = new ArrayList<>();
        String selectAllUser = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllUser,null);
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setName(cursor.getString(2));

                userList.add(user);
            }while (cursor.moveToNext());
        }
        return userList;
    }
}
