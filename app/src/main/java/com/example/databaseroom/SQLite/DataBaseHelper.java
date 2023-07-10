package com.example.databaseroom.SQLite;

import android.content.Context;
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

public class DataBaseHelper extends SQLiteOpenHelper {

//    private  static final String  DATABASE_NAME = "userStore.db";
//    private  static final int SCHEME =1;
//    public static final  String TABLE = "users";
//
//    public static final String COLUMN_NAME = "name";
//    public static final String COLUMN_YEAR = "year";

    private static String DB_PATH;
    private static final String DB_NAME = "Сars.db";
    private  static final int SCHEME_CARS = 1;
    public static final String TABLE_CARS = "cars";

    public  static final  String COLUMN_CARS_ID = "_id";
    public static final String COLUMN_CARS_NAME = "name";
    public static final String COLUMN_CARS_SPEED = "speed";
    private  Context context;




    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEME_CARS);
        this.context = context;
        DB_PATH = context.getFilesDir().getPath() + DB_PATH;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE);
//        onCreate(sqLiteDatabase);

    }



    public void create_db(){

        File file = new File(DB_PATH);
        if (!file.exists()) {
            //получаем локальную бд как поток
            try(InputStream myInput = context.getAssets().open(DB_NAME);
                // Открываем пустую бд
                OutputStream myOutput = Files.newOutputStream(Paths.get(DB_PATH))) {

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

        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }

}
