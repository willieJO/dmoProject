package br.edu.ifsp.dmo5.alugel.carros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.utils.UserSeason;

public class CarroXUserSQLite implements  ICarroXUser{


    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public CarroXUserSQLite(Context context){
        mHelper = new SQLiteHelper(context);
    }



    public static String createTable() {
        String sql = " CREATE TABLE " + Constant.DATABASE_CARRO_XUSER + "(";
        sql += Constant.DATABASE_ID + " INTEGER PRIMARY KEY, ";
        sql += Constant.CARRO_ID + " INTEGER, ";
        sql += Constant.USER_ID + " INTEGER ); ";
        return sql;
    }

    @Override
    public void alugar(int id) {
        ContentValues values = new ContentValues();
        values.put(Constant.USER_ID, UserSeason.getInstance().getUser().getId());
        values.put(Constant.CARRO_ID, id);
        mDatabase = mHelper.getWritableDatabase();
        long lines = mDatabase
                .insert(Constant.DATABASE_CARRO_XUSER, null, values);

        ContentValues valuesUpdate = new ContentValues();
        valuesUpdate.put(Constant.DATABASE_ALUGADO, 1);

        String selectionUpdate = Constant.DATABASE_ID + " = ?";
        String[] selectionArgsUpdate = {String.valueOf(id)};

        mDatabase = mHelper.getWritableDatabase();
        mDatabase.update(Constant.DATABASE_CARRO, valuesUpdate, selectionUpdate, selectionArgsUpdate);
        mDatabase.close();
    }
}
