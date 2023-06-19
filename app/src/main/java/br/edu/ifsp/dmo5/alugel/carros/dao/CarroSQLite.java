package br.edu.ifsp.dmo5.alugel.carros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.utils.UserSeason;

public class CarroSQLite implements ICarroDao {

    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public CarroSQLite(Context context){
        mHelper = new SQLiteHelper(context);
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
        sql += Constant.ACTIVE + " INTEGER DEFAULT 1, ";
        sql += Constant.DATABASE_ALUGADO + " INTEGER DEFAULT 0, ";
        sql += "FOREIGN KEY (" + Constant.ID_DONO_CARRO + ") REFERENCES " + Constant.DATABASE_CARRO + "(" + Constant.DATABASE_ID + "));";
        return sql;
    }

    @Override
    public boolean create(Carro carro) {
        ContentValues values = new ContentValues();
        values.put( Constant.ID_DONO_CARRO,UserSeason.getInstance().getUser().getId());
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

    @Override
    public List<Carro> findAll() {
        if (Objects.equals(UserSeason.getInstance().getUser().getCnh(), "")) {
            List<Carro> carro = new ArrayList<>();
            return carro;
        }
        List<Carro> list = new ArrayList<>();
        Cursor cursor;
        String[] projection = {
                Constant.DATABASE_ID,
                Constant.ID_DONO_CARRO,
                Constant.MARCA,
                Constant.MMODELO,
                Constant.COR,
                Constant.PLACA,
                Constant.TIPO_COMBUSTIVEL,
                Constant.AR,
                Constant.PORTAS,
                Constant.ELETRICO,
                Constant.RADIO,
                Constant.QUILOMETRAGEM,
                Constant.CRLVE,
                Constant.CPF_PROPRIETARIO,
                Constant.PHOTO
        };
        String selection = Constant.DATABASE_ALUGADO + " = ? AND " + Constant.ACTIVE + " = ?";
        String[] selectionArgs = {"0", "1"};
        mDatabase = mHelper.getReadableDatabase();
        cursor = mDatabase.query(
                Constant.DATABASE_CARRO,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Constant.DATABASE_ID));
            String idDonoCarro = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ID_DONO_CARRO));
            String marca = cursor.getString(cursor.getColumnIndexOrThrow(Constant.MARCA));
            String modelo = cursor.getString(cursor.getColumnIndexOrThrow(Constant.MMODELO));
            String cor = cursor.getString(cursor.getColumnIndexOrThrow(Constant.COR));
            String placa = cursor.getString(cursor.getColumnIndexOrThrow(Constant.PLACA));
            String tipoCombustivel = cursor.getString(cursor.getColumnIndexOrThrow(Constant.TIPO_COMBUSTIVEL));
            String arCondicionado = cursor.getString(cursor.getColumnIndexOrThrow(Constant.AR));
            String porta = cursor.getString(cursor.getColumnIndexOrThrow(Constant.PORTAS));
            String eletrico = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ELETRICO));
            String radio = cursor.getString(cursor.getColumnIndexOrThrow(Constant.RADIO));
            String quilometragem = cursor.getString(cursor.getColumnIndexOrThrow(Constant.QUILOMETRAGEM));
            String crlve = cursor.getString(cursor.getColumnIndexOrThrow(Constant.CRLVE));
            String CPF_PROPRIETARIO = cursor.getString(cursor.getColumnIndexOrThrow(Constant.CPF_PROPRIETARIO));
            String FOTO = cursor.getString(cursor.getColumnIndexOrThrow(Constant.PHOTO));
            Carro carroInsert = new Carro(id,idDonoCarro,marca,modelo,cor,placa,tipoCombustivel,arCondicionado,porta,eletrico,radio,quilometragem,crlve,CPF_PROPRIETARIO,FOTO);
            list.add(carroInsert);
        }

        cursor.close();
        mDatabase.close();

        return list;
    }

    @Override
    public List<Carro> findAllById() {
        if (Objects.equals(UserSeason.getInstance().getUser().getCnh(), "")) {
            List<Carro> carro = new ArrayList<>();
            return carro;
        }
        List<Carro> list = new ArrayList<>();
        Cursor cursor;
        String[] projection = {
                Constant.DATABASE_ID,
                Constant.ID_DONO_CARRO,
                Constant.MARCA,
                Constant.MMODELO,
                Constant.COR,
                Constant.PLACA,
                Constant.TIPO_COMBUSTIVEL,
                Constant.AR,
                Constant.PORTAS,
                Constant.ELETRICO,
                Constant.RADIO,
                Constant.QUILOMETRAGEM,
                Constant.CRLVE,
                Constant.CPF_PROPRIETARIO,
                Constant.PHOTO
        };
        int userId = UserSeason.getInstance().getUser().getId();
        String selection = Constant.ID_DONO_CARRO + " = ? AND " + Constant.DATABASE_ALUGADO + " = ? AND " + Constant.ACTIVE + " = ?";
        String[] selectionArgs = {String.valueOf(userId), "0", "1"};
        mDatabase = mHelper.getReadableDatabase();
        cursor = mDatabase.query(
                Constant.DATABASE_CARRO,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(Constant.DATABASE_ID));
            String idDonoCarro = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ID_DONO_CARRO));
            String marca = cursor.getString(cursor.getColumnIndexOrThrow(Constant.MARCA));
            String modelo = cursor.getString(cursor.getColumnIndexOrThrow(Constant.MMODELO));
            String cor = cursor.getString(cursor.getColumnIndexOrThrow(Constant.COR));
            String placa = cursor.getString(cursor.getColumnIndexOrThrow(Constant.PLACA));
            String tipoCombustivel = cursor.getString(cursor.getColumnIndexOrThrow(Constant.TIPO_COMBUSTIVEL));
            String arCondicionado = cursor.getString(cursor.getColumnIndexOrThrow(Constant.AR));
            String porta = cursor.getString(cursor.getColumnIndexOrThrow(Constant.PORTAS));
            String eletrico = cursor.getString(cursor.getColumnIndexOrThrow(Constant.ELETRICO));
            String radio = cursor.getString(cursor.getColumnIndexOrThrow(Constant.RADIO));
            String quilometragem = cursor.getString(cursor.getColumnIndexOrThrow(Constant.QUILOMETRAGEM));
            String crlve = cursor.getString(cursor.getColumnIndexOrThrow(Constant.CRLVE));
            String CPF_PROPRIETARIO = cursor.getString(cursor.getColumnIndexOrThrow(Constant.CPF_PROPRIETARIO));
            String FOTO = cursor.getString(cursor.getColumnIndexOrThrow(Constant.PHOTO));
            Carro carroInsert = new Carro(id,idDonoCarro,marca,modelo,cor,placa,tipoCombustivel,arCondicionado,porta,eletrico,radio,quilometragem,crlve,CPF_PROPRIETARIO,FOTO);
            list.add(carroInsert);
        }

        cursor.close();
        mDatabase.close();

        return list;
    }

    @Override
    public void excluirCarro(Carro carro) {
        mDatabase = mHelper.getWritableDatabase();
        String selection = Constant.DATABASE_ID + " = ?";
        String[] selectionArgs = { String.valueOf(carro.getId()) };
        ContentValues values = new ContentValues();
        values.put(Constant.ACTIVE, 0);
        mDatabase.update(Constant.DATABASE_CARRO, values, selection, selectionArgs);
        mDatabase.close();
    }

    @Override
    public void edit(Carro carro) {
        ContentValues values = new ContentValues();
        values.put(Constant.ID_DONO_CARRO, UserSeason.getInstance().getUser().getId());
        values.put(Constant.MARCA, carro.getMarca());
        values.put(Constant.MMODELO, carro.getMarca());
        values.put(Constant.COR, carro.getCor());
        values.put(Constant.PLACA, carro.getPlaca());
        values.put(Constant.TIPO_COMBUSTIVEL, carro.getCombustivel());
        values.put(Constant.AR, carro.getArCondicionado());
        values.put(Constant.PORTAS, carro.getPorta());
        values.put(Constant.ELETRICO, carro.getEletrico());
        values.put(Constant.RADIO, carro.getRadio());
        values.put(Constant.QUILOMETRAGEM, carro.getQuilometragem());
        values.put(Constant.CRLVE, carro.getCrlve());
        values.put(Constant.CPF_PROPRIETARIO, carro.getCpf());
        values.put(Constant.PHOTO, carro.getFoto());

        String whereClause = Constant.DATABASE_ID + " = ?";
        String[] whereArgs = {String.valueOf(carro.getId())};

        mDatabase = mHelper.getWritableDatabase();
        mDatabase.update(Constant.DATABASE_CARRO, values, whereClause, whereArgs);
        mDatabase.close();
    }
}



