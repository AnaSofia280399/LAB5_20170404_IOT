package com.example.lab5_20170404_iot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "AppTareas.db";

    public DatabaseHelper(@Nullable Context context){
        super(context, "AppTareas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase){
        MyDatabase.execSQL("create Table allusers(codigo TEXT primary key, password TEXT, nombre TEXT)");
        //Podria agregar otra tabla con tareas por usuarios
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("drop Table if exists allusers");
    }

    public Boolean insertData(String codigo, String password, String nombre){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo", codigo);
        contentValues.put("nombre", nombre);
        contentValues.put("password", password);

        long result = MyDatabase.insert("allusers", null, contentValues);

        if(result == -1){
            return false;

        }else {
            return true;
        }
    }

    public Boolean checkCodigo(String codigo){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where codigo = ?", new String[]{codigo});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }

    }

    public Boolean checkPassword(String codigo, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where codigo = ? and password = ?", new String[]{codigo, password});

        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }

    }
}
