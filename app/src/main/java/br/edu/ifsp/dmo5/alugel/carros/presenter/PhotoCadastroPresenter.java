package br.edu.ifsp.dmo5.alugel.carros.presenter;

import br.edu.ifsp.dmo5.alugel.carros.dao.CarroSQLite;
import br.edu.ifsp.dmo5.alugel.carros.dao.ICarroDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.IUserDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.UserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.mvp.PhotoCadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.VeiculoCadastroMVP;

public class PhotoCadastroPresenter implements PhotoCadastroMVP.Presenter {
    private PhotoCadastroMVP.View view;
    private ICarroDao dao;


    public PhotoCadastroPresenter(PhotoCadastroMVP.View viewRecept) {
        view = viewRecept;
        dao = new CarroSQLite(view.getContext());
    }

    @Override
    public void salvarVeiculo() {
        view.retornarBase64();
    }

    @Override
    public void deatach() {
        view = null;
    }
}
