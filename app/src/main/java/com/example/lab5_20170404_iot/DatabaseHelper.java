package com.example.lab5_20170404_iot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "App2Tareas.db";

    public DatabaseHelper(@Nullable Context context){
        super(context, "App2Tareas.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase){
        MyDatabase.execSQL("create Table allusers(codigo TEXT primary key, password TEXT, nombre TEXT)");
        //Podria agregar otra tabla con tareas por usuarios


        // Crear la tabla de tareas
        MyDatabase.execSQL(
                "create Table tareas(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "titulo TEXT, " +
                        "descripcion TEXT, " +
                        "fecha DATE, " +  // Almacena solo la fecha
                        "hora TIME, " +   // Almacena solo la hora
                        "completada BOOLEAN DEFAULT 0, " +  // 0 = false, 1 = true
                        "codigo_usuario TEXT, " +
                        "FOREIGN KEY(codigo_usuario) REFERENCES allusers(codigo) ON DELETE CASCADE)"
        );
    }

    public Boolean insertData2(String codigo, String titulo, String descripcion, String fecha, String hora){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codigo_usuario", codigo);
        contentValues.put("titulo", titulo);
        contentValues.put("descripcion", descripcion);
        contentValues.put("completada", 0);
        contentValues.put("fecha", fecha);
        contentValues.put("hora", hora);

        long result = MyDatabase.insert("tareas", null, contentValues);

        if(result == -1){
            return false;

        }else {
            return true;
        }
    }

    public List<Tarea> getTareasNoCompletadasPorUsuario(String codigoUsuario) {
        List<Tarea> tareas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tareas WHERE codigo_usuario = ? AND completada = 0", new String[] {codigoUsuario});

        if (cursor.moveToFirst()) {
            int indexTitulo = cursor.getColumnIndex("titulo");
            int indexDescripcion = cursor.getColumnIndex("descripcion");
            int indexFecha = cursor.getColumnIndex("fecha");
            int indexHora = cursor.getColumnIndex("hora");

            if (indexTitulo == -1 || indexDescripcion == -1 || indexFecha == -1 || indexHora == -1) {
                throw new IllegalArgumentException("Una o más columnas no fueron encontradas en la base de datos.");
            }

            do {
                String titulo = cursor.getString(indexTitulo);
                String descripcion = cursor.getString(indexDescripcion);
                String fecha = cursor.getString(indexFecha);
                String hora = cursor.getString(indexHora);

                Tarea tarea = new Tarea(codigoUsuario,titulo, descripcion, fecha, hora);
                tareas.add(tarea);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tareas;
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
