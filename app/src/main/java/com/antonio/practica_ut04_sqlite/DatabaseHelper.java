package com.antonio.practica_ut04_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "usuarios_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_USER = "usuarios";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "nombre";
    private static final String KEY_EMAIL = "email";

    /*Crea la tabla "contactos" */

    private static final String CREATE_TABLE_CONTACTOS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT, "+ KEY_EMAIL + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_CONTACTOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }

    public long addUserDetail(String nombre, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Crea los valores
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, nombre);
        values.put(KEY_EMAIL, email);
        // insert row in students table
        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }

    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> userModelArrayList = new ArrayList<UserModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                userModel.setNombre(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                userModel.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
                // adding to Students list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public int updateUser(int id, String nombre, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Crea los valores
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, nombre);
        values.put(KEY_EMAIL, email);
        // update row in students table base on students.is value
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // borra la fila en base al id de la tabla "contactos"
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }



}
