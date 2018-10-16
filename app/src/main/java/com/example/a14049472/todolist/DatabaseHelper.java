package com.example.a14049472.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 26/12/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // final is a constant

    public static final String DATABASE_NAME = "mylist.db";
    public  static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";

    public DatabaseHelper(Context context){
        super (context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = " CREATE TABLE " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
               " ITEM1 TEXT)";
                db.execSQL(createTable);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXITS "+ TABLE_NAME);
        onCreate(db);




    }
    public boolean addData(String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);


        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as instered incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
        /**
         * Return all the data from database
         * @return
         */
        //cursor is know indirect subclass
        public Cursor getListContent() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data =db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;


    }
    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public  Cursor getItemID(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "SELECT "+ COL1 +" FROM "+TABLE_NAME+
                " WHERE "+ COL2 +" = '"+ name +"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }


    /**
     * Delete from database
     * @param
     * @param name
     */
    public void deleteName(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String query= "DELETE FROM "+ TABLE_NAME +" WHERE " + COL2 + "= '" + name + "'";
        db.execSQL(query);

    }


}
