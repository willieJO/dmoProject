package br.edu.ifsp.dmo5.alugel.carros.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "project.db";
    public static final int DATABASE_VERSION = 11;

    public SQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = UserSQLite.createTable();
        sql += CarroSQLite.createTable();
        sql += CarroXUserSQLite.createTable();
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 10:
                String sql = CarroXUserSQLite.createTable();
                db.execSQL(sql);
                break;
        }
    }
}
