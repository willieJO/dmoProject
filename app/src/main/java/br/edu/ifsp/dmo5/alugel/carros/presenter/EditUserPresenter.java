package br.edu.ifsp.dmo5.alugel.carros.presenter;

import java.util.Objects;

import br.edu.ifsp.dmo5.alugel.carros.dao.IUserDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.UserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.model.User;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.EditUserMVP;
import br.edu.ifsp.dmo5.alugel.carros.utils.Criptografia;
import br.edu.ifsp.dmo5.alugel.carros.utils.UserSeason;

public class EditUserPresenter  implements EditUserMVP.Presenter {
    private EditUserMVP.View view;
    private IUserDao dao;

    public EditUserPresenter(EditUserMVP.View view) {
        this.view = view;
        dao = new UserSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public boolean realizarCadastro(User user) {
        if (!Objects.equals(user.getSenha(), UserSeason.getInstance().getUser().getSenha())) {
            user.setSenha(Criptografia.criptografar(user.getSenha()));
        }
        if (!dao.edit(user)){
            view.showToast("Algum erro no servidor ocorreu, tente novamente depois");
        } else {
            view.showToast("Alteração realizada");
        }
        return true;
    }
}
