package com.diegopereira.cartolafc.parciais;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diegopereira.cartolafc.teste.TimePontos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "Parciais";

    // Table columns
    public static final String _ID = "_id";
    public static final String RODADA = "rodada";
    public static final String NOME = "nome";
    public static final String PARCIAIS = "parciais";
    public static final String ID = "id";
    public static final String SCOUTS = "scouts";



    // Database Information
    static final String DB_NAME = "PARCIAIS.DB";

    // database version
    static final int DB_VERSION = 2;

    // Creating table query
    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RODADA + " INTEGER, " + NOME + " TEXT NOT NULL, " + PARCIAIS + " REAL, " + ID + " INTEGER, " + SCOUTS + " TEXT NOT NULL);";

    public DatabaseHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL(CREATE_TABLE);
            onCreate(db);
        }
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert( Integer rodada, String nome, double parciais, int id, Map<String, Integer> scouts ) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(RODADA, rodada);
        contentValue.put(NOME, nome);
        contentValue.put(PARCIAIS, parciais);
        contentValue.put(ID, id);
        contentValue.put(SCOUTS, String.valueOf(scouts));


        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValue);
        db.close();
    }

    public void update(long _id, String nome) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOME, nome);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, contentValues, _ID + " = " + _id, null);
        db.close();
    }

    public List<TimePontos> getTimes() {
        List<TimePontos> array_list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME+" ORDER BY cast(total AS REAL) ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                TimePontos timePontos = new TimePontos();
                timePontos.setNome(cursor.getString(1));
                timePontos.setParciais(cursor.getDouble(2));
                timePontos.setPontosrod(cursor.getDouble(3));
                timePontos.setUrlEscudoPng(cursor.getString(4));
                timePontos.setQty(cursor.getString(5));
                timePontos.setTimeId(cursor.getInt(6));

                array_list.add(0, timePontos);
            } while (cursor.moveToNext());
        }

            db.close();
            return array_list;
    }

    public Cursor fetch() {
        String sql = "SELECT * FROM " + TABLE_NAME+" ORDER BY cast(total AS REAL) DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }

}
