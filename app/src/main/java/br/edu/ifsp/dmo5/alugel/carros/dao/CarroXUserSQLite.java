package br.edu.ifsp.dmo5.alugel.carros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.model.CarroXUserAlugado;
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
        sql += Constant.DATABASE_DATA + " TEXT, ";
        sql += Constant.DATABASE_DATA_FIM + " TEXT, ";
        sql += Constant.DATABASE_CARRO_ENTREGUE + " INTEGER DEFAULT 0, ";
        sql += Constant.USER_ID + " INTEGER ); ";
        return sql;
    }

    @Override
    public void alugar(int id,String data,String dataFim) {
        ContentValues values = new ContentValues();
        values.put(Constant.USER_ID, UserSeason.getInstance().getUser().getId());
        values.put(Constant.DATABASE_DATA, data);
        values.put(Constant.DATABASE_DATA_FIM, dataFim);
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

    @Override
    public List<CarroXUserAlugado> obterCarrosAlugados() {
        String query = "SELECT " +
                "CarroXUser." + Constant.DATABASE_ID + ", " +
                "CarroXUser." + Constant.DATABASE_DATA + ", " +
                "CarroXUser." + Constant.CARRO_ID + ", " +
                "CarroXUser." + Constant.DATABASE_CARRO_ENTREGUE + ", " +
                "Carro." + Constant.MMODELO + ", " +
                "Carro." + Constant.PHOTO + " " +
                "FROM " + Constant.DATABASE_CARRO_XUSER + " " +
                "INNER JOIN " + Constant.DATABASE_CARRO + " ON " +
                "CarroXUser.CarroId = Carro." + Constant.DATABASE_ID + " " +
                "WHERE CarroXUser.UserId = ?";

        String[] selectionArgs = {String.valueOf(UserSeason.getInstance().getUser().getId())};
        mDatabase = mHelper.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery(query, selectionArgs);

        List<CarroXUserAlugado> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            CarroXUserAlugado carro = new CarroXUserAlugado();
            carro.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constant.DATABASE_ID)));
            carro.setDataInicio(cursor.getString(cursor.getColumnIndexOrThrow(Constant.DATABASE_DATA)));
            carro.setCarroEntregue(cursor.getInt(cursor.getColumnIndexOrThrow(Constant.DATABASE_CARRO_ENTREGUE)));
            carro.setModeloCarro(cursor.getString(cursor.getColumnIndexOrThrow(Constant.MMODELO)));
            carro.setFoto(cursor.getString(cursor.getColumnIndexOrThrow(Constant.PHOTO)));
            carro.setCarroId(cursor.getInt(cursor.getColumnIndexOrThrow(Constant.CARRO_ID)));
            list.add(carro);
        }

        cursor.close();
        mDatabase.close();
        return list;
    }

    @Override
    public void entregar(CarroXUserAlugado carro) {
        mDatabase = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.DATABASE_CARRO_ENTREGUE, 1);

        String selection = Constant.DATABASE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(carro.getId())};
        mDatabase.update(Constant.DATABASE_CARRO_XUSER, values, selection, selectionArgs);
        mDatabase.close();
        updateValorAlugado(carro);
    }

    @Override
    public void cancelar(CarroXUserAlugado carro) {
        mDatabase = mHelper.getWritableDatabase();
        String selection = Constant.DATABASE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(carro.getId())};
        mDatabase.delete(Constant.DATABASE_CARRO_XUSER, selection, selectionArgs);
        mDatabase.close();
        updateValorAlugado(carro);
    }
    public void updateValorAlugado(CarroXUserAlugado carro) {
        mDatabase = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constant.DATABASE_ALUGADO, 0);

        String selection = Constant.DATABASE_ID + " = ?";
        String[] selectionArgs = {String.valueOf(carro.getCarroId())};

        int rowsAffected = mDatabase.update(Constant.DATABASE_CARRO, values, selection, selectionArgs);

        mDatabase.close();
    }
}
