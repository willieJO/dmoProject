package br.edu.ifsp.dmo5.alugel.carros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.utils.UserSeason;

public class CarroSQLite implements ICarroDao {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public CarroSQLite(Context context){
        mHelper = new SQLiteHelper(context);
    }

    @Override
    public boolean salvarCarro(Carro carro) {

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
        sql += Constant.PHOTO + " TEXT, ";
        sql += "FOREIGN KEY (" + Constant.ID_DONO_CARRO + ") REFERENCES " + Constant.DATABASE_CARRO + "(" + Constant.DATABASE_ID + "));";
        return sql;
    }

    @Override
    public boolean create(Carro carro) {
        ContentValues values = new ContentValues();
        values.put( Constant.ID_DONO_CARRO,UserSeason.getInstance().getUserId());
        values.put(Constant.MARCA,carro.getMarca());
        values.put(Constant.MMODELO,carro.getMarca());
        values.put(Constant.COR,carro.getCor());
        values.put(Constant.PLACA,carro.getPlaca());
        values.put(Constant.TIPO_COMBUSTIVEL,carro.getCombustivel());
        values.put(Constant.AR,carro.getArCondicionado());
        values.put(Constant.PORTAS,carro.getPorta());
        values.put(Constant.ELETRICO,carro.getEletrico());
        values.put(Constant.RADIO,carro.getRadio());
        values.put(Constant.QUILOMETRAGEM,carro.getQuilometragem());
        values.put(Constant.CRLVE,carro.getCrlve());
        values.put(Constant.CPF_PROPRIETARIO,carro.getCpf());
        values.put(Constant.PHOTO,carro.getFoto());
        mDatabase = mHelper.getWritableDatabase();
        long lines = mDatabase
                .insert(Constant.DATABASE_CARRO, null, values);
        mDatabase.close();
        return lines == -1 ? false : true;
    }
}


