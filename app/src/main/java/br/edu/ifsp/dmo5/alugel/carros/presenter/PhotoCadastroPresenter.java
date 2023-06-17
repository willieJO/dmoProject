package br.edu.ifsp.dmo5.alugel.carros.presenter;

import android.content.Intent;

import br.edu.ifsp.dmo5.alugel.carros.dao.CarroSQLite;
import br.edu.ifsp.dmo5.alugel.carros.dao.ICarroDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.IUserDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.UserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.PhotoCadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.VeiculoCadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.view.UserActivity;

public class PhotoCadastroPresenter implements PhotoCadastroMVP.Presenter {
    private PhotoCadastroMVP.View view;
    private ICarroDao dao;


    public PhotoCadastroPresenter(PhotoCadastroMVP.View viewRecept) {
        view = viewRecept;
        dao = new CarroSQLite(view.getContext());
    }

    @Override
    public void salvarVeiculo() {
        Carro carro = view.getCarro();
        String base64 = view.retornarBase64();
        carro.setFoto(base64);
        dao.create(carro);
        Intent intent = new Intent(view.getContext(), UserActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void deatach() {
        view = null;
    }
}
