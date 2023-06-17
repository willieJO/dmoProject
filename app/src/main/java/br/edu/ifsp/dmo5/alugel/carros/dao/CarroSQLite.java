package br.edu.ifsp.dmo5.alugel.carros.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;

public class CarroSQLite implements ICarroDao {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public CarroSQLite(Context context){
        mHelper = new SQLiteHelper(context);
    }

    @Override
    public boolean salvarCarro() {
        return false;
    }


    public static String createTable() {
        String sql = " CREATE TABLE " + Constant.DATABASE_CARRO + "(";
        sql += Constant.DATABASE_ID + " INTEGER PRIMARY KEY, ";
        sql += Constant.ID_DONO_CARRO + " INTEGER, ";
        sql += Constant.MARCA + " TEXT, ";
        sql += Constant.MMODELO + " TEXT, ";
        sql += Constant.COR + " TEXT, ";
        sql += Constant.PLACA + " TEXT, ";
        sql += Constant.TIPO_COMBUSTIVEL + " TEXT, ";
        sql += Constant.AR + " TEXT, ";
        sql += Constant.PORTAS + " TEXT, ";
        sql += Constant.ELETRICO + " TEXT, ";
        sql += Constant.RADIO + " TEXT, ";
        sql += Constant.QUILOMETRAGEM + " TEXT, ";
        sql += Constant.CRLVE + " TEXT, ";
        sql += Constant.CPF_PROPRIETARIO + " TEXT, ";
        sql += sql += Constant.PHOTO + " TEXT, ";
        sql += "FOREIGN KEY (" + Constant.ID_DONO_CARRO + ") REFERENCES " + Constant.DATABASE_CARRO + "(" + Constant.DATABASE_ID + "));";
        return sql;
    }

    @Override
    public boolean create(Carro carro) {
        return false;
    }
}
