package br.edu.ifsp.dmo5.alugel.carros.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.dao.IUserDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.UserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.utils.Criptografia;
import br.edu.ifsp.dmo5.alugel.carros.utils.UserSeason;
import br.edu.ifsp.dmo5.alugel.carros.view.NewUserActivity;
import br.edu.ifsp.dmo5.alugel.carros.view.UserActivity;

public class MainPresenter  implements MainMVP.Presenter {
    private MainMVP.View view;
    private IUserDao dao;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = new UserSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void realizarLogin(String email, String pass, boolean manterConetado) {
        String senhaSemCriptografa = pass;
        String senhaCriptografada = Criptografia.criptografar(pass);
        boolean result = dao.realizarLogin(email, senhaCriptografada);
        if (!result) {
            view.showToast("Dados invalidos");

        } else {
            if (manterConetado) {
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(Constant.USER_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Constant.USER_ID, UserSeason.getInstance().getUser().getId());
                editor.putString(Constant.DATABASE_EMAIL,email);
                editor.putString(Constant.DATABASE_SENHA,senhaSemCriptografa);
                editor.apply();
            }
            Intent intent = new Intent(view.getContext(), UserActivity.class);
            view.getContext().startActivity(intent);
        }
    }

    @Override
    public void openCadastro() {
        Intent intent = new Intent(view.getContext(), NewUserActivity.class);
        view.getContext().startActivity(intent);
    }
}
