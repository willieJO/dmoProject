package br.edu.ifsp.dmo5.alugel.carros.presenter;

import android.content.Intent;

import br.edu.ifsp.dmo5.alugel.carros.dao.IUserDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.UserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.utils.Criptografia;
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
    public void realizarLogin(String email, String pass) {
        pass = Criptografia.criptografar(pass);
        boolean result = dao.realizarLogin(email, pass);
        if (!result) {
            view.showToast("Dados invalidos");
        } else {
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
