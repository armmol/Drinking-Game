package com.example.drinkinggame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database_Helper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "History.db";
    public static final String TABLE_NAME = "History_table";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "Rounds_Played";
    public static final String COL_3 = "Drunk_Percentage";

    public Database_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, Rounds_Played INTEGER, Drunk_Percentage INTEGER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertdata(String Name,String Rounds_Played, String Drunk_Percentage)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,Name);
        contentValues.put(COL_2,Rounds_Played);
        contentValues.put(COL_3,Drunk_Percentage);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Integer delete(String ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {ID});
    }



}
