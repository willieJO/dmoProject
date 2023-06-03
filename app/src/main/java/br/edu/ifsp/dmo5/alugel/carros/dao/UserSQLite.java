package br.edu.ifsp.dmo5.alugel.carros.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.model.User;

public class UserSQLite implements IUserDao{
    private SQLiteHelper mHelper;
    private SQLiteDatabase mDatabase;

    public UserSQLite(Context context){
        mHelper = new SQLiteHelper(context);
    }

    public static String createTable(){
        String sql = "CREATE TABLE " + Constant.DATABASE_USER + "(";
        sql += Constant.DATABASE_ID + " INTEGER PRIMARY KEY, ";
        sql += Constant.DATABASE_NOME + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_CPF + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_CNH + " TEXT, ";
        sql += Constant.DATABASE_CNH_VALIDADE + " TEXT, ";
        sql += Constant.DATABASE_LOGADOURO+ " TEXT NOT NULL, ";
        sql += Constant.DATABASE_NUMERO + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_BAIRRO + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_CIDADE + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_ESTADO + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_CEP + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_TELEFONE + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_EMAIL + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_DATA_NASCIMENTO + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_SENHA + " TEXT NOT NULL )";
        return sql;
    }

    @Override
    public boolean create(User locador) {
        ContentValues values = new ContentValues();
        values.put(Constant.DATABASE_NOME,locador.getNome());
        values.put(Constant.DATABASE_CPF,locador.getCpf());
        values.put(Constant.DATABASE_CNH,locador.getCnh());
        values.put(Constant.DATABASE_CNH_VALIDADE,locador.getDataCnh());
        values.put(Constant.DATABASE_LOGADOURO,locador.getLogadouro());
        values.put(Constant.DATABASE_BAIRRO,locador.getBairro());
        values.put(Constant.DATABASE_NUMERO,locador.getNumero());
        values.put(Constant.DATABASE_CIDADE,locador.getCidade());
        values.put(Constant.DATABASE_ESTADO,locador.getEstado());
        values.put(Constant.DATABASE_CEP,locador.getCep());
        values.put(Constant.DATABASE_TELEFONE,locador.getTelefone());
        values.put(Constant.DATABASE_EMAIL,locador.getEmail());
        values.put(Constant.DATABASE_DATA_NASCIMENTO,locador.getDataDeNascimento());
        values.put(Constant.DATABASE_SENHA,locador.getSenha());

        mDatabase = mHelper.getWritableDatabase();
        long lines = mDatabase
                .insert(Constant.DATABASE_USER, null, values);
        mDatabase.close();
        return lines == -1 ? false : true;
    }

    @Override
    public boolean realizarLogin(String email, String pass) {
        boolean retorno = false;
        String columns[] = new String[]{
                Constant.DATABASE_EMAIL,
                Constant.DATABASE_SENHA
        };
        String where = Constant.DATABASE_EMAIL + " like '" + email + "'";
        where += " AND " + Constant.DATABASE_SENHA + " like '" + pass + "'";
        try {
            mDatabase = mHelper.getReadableDatabase();
            Cursor cursor = mDatabase.query(
                    Constant.DATABASE_USER,
                    columns,
                    where,
                    null,
                    null,
                    null,
                    null
            );

            if(cursor.moveToNext()){
                retorno = true;
            }
            cursor.close();
        } catch (Exception e) {
            retorno = false;
        } finally {
            mDatabase.close();
        }
        return  retorno;
    }
}
