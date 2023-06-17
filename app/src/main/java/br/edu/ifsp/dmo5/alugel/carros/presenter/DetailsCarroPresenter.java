package br.edu.ifsp.dmo5.alugel.carros.presenter;

import android.content.Intent;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.dao.CarroXUserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.dao.ICarroDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.ICarroXUser;
import br.edu.ifsp.dmo5.alugel.carros.dao.IUserDao;
import br.edu.ifsp.dmo5.alugel.carros.dao.UserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CarroDetailsMVP;
import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.view.UserActivity;

public class DetailsCarroPresenter implements CarroDetailsMVP.Presenter {
    private CarroDetailsMVP.View view;
    private ICarroXUser dao;

    public DetailsCarroPresenter(CarroDetailsMVP.View view) {
        this.view = view;
        dao = new CarroXUserSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void alugaCarro(int id) {
        dao.alugar(id);
        view.showToast(Constant.SUCESSO_ALUGUEL);
        Intent intent = new Intent(view.getContext(), UserActivity.class);
        view.getContext().startActivity(intent);
    }
}
