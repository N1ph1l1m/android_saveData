package com.example.databaseroom.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

//    private DB_helper dataBaseHelper;
//    private SQLiteDatabase database;
//
//    public DatabaseAdapter(Context context){
//        dataBaseHelper = new DB_helper(context.getApplicationContext());
//    }
//
//    public  DatabaseAdapter open (){
//        database = dataBaseHelper.getWritableDatabase();
//        return this;
//    }
//    public void close(){
//        dataBaseHelper.close();
//    }

//    public Cursor getAllEntries(){
//        String[] columns = new String[]{Util.COLUMN_ID,Util.COLUMN_NAME,Util.TABLE_NAME};
//        return  database.query(Util.TABLE_NAME, columns,
//                null,
//                null,
//                null,
//                null,
//                null);
//    }
//     public List<User>getUsers(){
//         ArrayList<User> users = new ArrayList<>();
//         Cursor cursor = getAllEntries();
//         while(cursor.moveToNext()){
//             int id = cursor.getInt(cursor.getColumnIndex(Util.COLUMN_ID));
//             String name = cursor.getString(cursor.getColumnIndex(Util.COLUMN_NAME));
//             int year = cursor.getInt(cursor.getColumnIndex(Util.COLUMN_YEAR));
//            users.add(new User(id,name,year));
//         }
//         cursor.close();
//         return users;
//     }

//    public long getCount(){
//        return DatabaseUtils.queryNumEntries(database, Util.TABLE_NAME);
//    }


//    public User getUser(long id){
//        User user = null;
//        String query = String.format(" SELECT * FROM %s WHERE %s=?" , Util.TABLE_NAME, Util.COLUMN_ID );
//        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(id)});
//        if(cursor.moveToFirst()){
//            String name = cursor.getString(cursor.getColumnIndex(Util.COLUMN_NAME));
//            int year = cursor.getInt(cursor.getColumnIndex(Util.COLUMN_YEAR));
//            user = new User(id,name,year);
//        }
//        cursor.close();
//        return user;
     }

//     public  long insert(User user){
//         ContentValues cv = new ContentValues();
//         cv.put(Util.COLUMN_NAME,user.getName());
//         cv.put(Util.COLUMN_YEAR,user.getYear());
//         return database.insert(Util.TABLE_NAME, null , cv);
//     }
//     public long delete(long userId){
//        String whereClause = "_id = ?";
//        String[] whereArgs = new String[]{String.valueOf(userId)};
//        return  database.delete(Util.TABLE_NAME, whereClause,whereArgs);
//     }
//     public long update(User user){
//        String whereClause = Util.COLUMN_ID + "=" + user.getId();
//        ContentValues cv = new ContentValues();
//        cv.put(Util.COLUMN_NAME, user.getName());
//        cv.put(Util.COLUMN_YEAR, user.getYear());
//        return database.update(Util.TABLE_NAME , cv , whereClause , null);
//     }


}
