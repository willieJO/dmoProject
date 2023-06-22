package br.edu.ifsp.dmo5.alugel.carros.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.model.User;
import br.edu.ifsp.dmo5.alugel.carros.utils.UserSeason;

public class UserSQLite implements IUserDao {
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
        sql += Constant.CNH_CATEGORIA + " TEXT NOT NULL, ";
        sql += Constant.DATABASE_SENHA + " TEXT NOT NULL ); ";
        return sql;
    }

    @Override
    public boolean create(User locador) {
        ContentValues values = new ContentValues();
        values.put(Constant.DATABASE_NOME,locador.getNome());
        values.put(Constant.DATABASE_CPF,locador.getCpf());
        values.put(Constant.CNH_CATEGORIA,locador.getCnhCategoria());
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
    public boolean edit(User locador) {
        ContentValues values = new ContentValues();
        values.put(Constant.DATABASE_NOME, locador.getNome());
        values.put(Constant.DATABASE_CPF, locador.getCpf());
        values.put(Constant.DATABASE_CNH, locador.getCnh());
        values.put(Constant.DATABASE_CNH_VALIDADE, locador.getDataCnh());
        values.put(Constant.DATABASE_LOGADOURO, locador.getLogadouro());
        values.put(Constant.DATABASE_BAIRRO, locador.getBairro());
        values.put(Constant.DATABASE_NUMERO, locador.getNumero());
        values.put(Constant.DATABASE_CIDADE, locador.getCidade());
        values.put(Constant.DATABASE_ESTADO, locador.getEstado());
        values.put(Constant.DATABASE_CEP, locador.getCep());
        values.put(Constant.DATABASE_TELEFONE, locador.getTelefone());
        values.put(Constant.DATABASE_EMAIL, locador.getEmail());
        values.put(Constant.DATABASE_DATA_NASCIMENTO, locador.getDataDeNascimento());
        values.put(Constant.CNH_CATEGORIA, locador.getCnhCategoria());
        values.put(Constant.DATABASE_SENHA, locador.getSenha());
        mDatabase = mHelper.getWritableDatabase();
        int rowsAffected = mDatabase.update(
                Constant.DATABASE_USER,
                values,
                Constant.DATABASE_ID + " = ?",
                new String[]{String.valueOf(UserSeason.getInstance().getUser().getId())}
        );
        UserSeason.getInstance().setUser(locador);
        mDatabase.close();
        return rowsAffected > 0;
    }

    @SuppressLint("Range")
    @Override
    public boolean realizarLogin(String email, String pass) {
        boolean retorno = false;
        String columns[] = new String[]{
                Constant.DATABASE_ID,
                Constant.DATABASE_EMAIL,
                Constant.DATABASE_SENHA,
                Constant.DATABASE_NOME,
                Constant.DATABASE_CPF,
                Constant.DATABASE_CNH_VALIDADE,
                Constant.DATABASE_LOGADOURO,
                Constant.DATABASE_BAIRRO,
                Constant.DATABASE_NUMERO,
                Constant.DATABASE_CIDADE,
                Constant.DATABASE_ESTADO,
                Constant.DATABASE_CEP,
                Constant.DATABASE_TELEFONE,
                Constant.DATABASE_DATA_NASCIMENTO,
                Constant.DATABASE_CNH,
                Constant.CNH_CATEGORIA
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
                User user = new User();
                UserSeason.getInstance().setUser(user);
                UserSeason.getInstance().getUser().setId(cursor.getInt(cursor.getColumnIndex(Constant.DATABASE_ID)));
                UserSeason.getInstance().getUser().setEmail(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_EMAIL)));
                UserSeason.getInstance().getUser().setSenha(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_SENHA)));
                UserSeason.getInstance().getUser().setNome(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_NOME)));
                UserSeason.getInstance().getUser().setCpf(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_CPF)));
                UserSeason.getInstance().getUser().setDataCnh(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_CNH_VALIDADE)));
                UserSeason.getInstance().getUser().setLogadouro(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_LOGADOURO)));
                UserSeason.getInstance().getUser().setBairro(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_BAIRRO)));
                UserSeason.getInstance().getUser().setNumero(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_NUMERO)));
                UserSeason.getInstance().getUser().setCidade(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_CIDADE)));
                UserSeason.getInstance().getUser().setEstado(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_ESTADO)));
                UserSeason.getInstance().getUser().setCep(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_CEP)));
                UserSeason.getInstance().getUser().setBairro(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_BAIRRO)));
                UserSeason.getInstance().getUser().setTelefone(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_TELEFONE)));
                UserSeason.getInstance().getUser().setDataDeNascimento(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_DATA_NASCIMENTO)));
                UserSeason.getInstance().getUser().setCnhCategoria(cursor.getString(cursor.getColumnIndex(Constant.CNH_CATEGORIA)));
                UserSeason.getInstance().getUser().setCnh(cursor.getString(cursor.getColumnIndex(Constant.DATABASE_CNH)));
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
