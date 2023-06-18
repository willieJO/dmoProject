package br.edu.ifsp.dmo5.alugel.carros.presenter;

import android.content.Intent;

import br.edu.ifsp.dmo5.alugel.carros.dao.IUserDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.UserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.model.User;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.utils.Criptografia;
import br.edu.ifsp.dmo5.alugel.carros.view.LeiaMeActivity;
import br.edu.ifsp.dmo5.alugel.carros.view.UserActivity;

public class CadastroPresenter implements CadastroMVP.Presenter {
    private CadastroMVP.View view;
    private IUserDao dao;

    public CadastroPresenter(CadastroMVP.View view) {
        this.view = view;
        dao = new UserSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public boolean realizarCadastro(User user) {
        user.setSenha(Criptografia.criptografar(user.getSenha()));
        if (!dao.create(user)){
            view.showToast("Algum erro no servidor ocorreu, tente novamente depois");
        }
        return true;
    }

    @Override
    public void leiame() {
        Intent intent = new Intent(view.getContext(), LeiaMeActivity.class);
        view.getContext().startActivity(intent);
    }


}
