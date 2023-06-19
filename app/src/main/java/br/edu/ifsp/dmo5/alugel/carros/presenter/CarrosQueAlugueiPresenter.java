package br.edu.ifsp.dmo5.alugel.carros.presenter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.dmo5.alugel.carros.dao.CarroXUserSQLite;
import br.edu.ifsp.dmo5.alugel.carros.dao.ICarroXUser;
import br.edu.ifsp.dmo5.alugel.carros.model.CarroXUserAlugado;
import br.edu.ifsp.dmo5.alugel.carros.mvp.CarrosQueAlugueiMVP;
import br.edu.ifsp.dmo5.alugel.carros.view.adapter.CarrosQueAlugueiPocketRecyclerAdapter;

public class CarrosQueAlugueiPresenter implements CarrosQueAlugueiMVP.Presenter{
    private CarrosQueAlugueiMVP.View view;
    private ICarroXUser dao;

    public CarrosQueAlugueiPresenter(CarrosQueAlugueiMVP.View recipeView) {
        view = recipeView;
        dao = new CarroXUserSQLite(view.getContext());
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void entregar(CarroXUserAlugado carro) {
        dao.entregar(carro);
    }

    @Override
    public void cancelar(CarroXUserAlugado carro) {
        dao.cancelar(carro);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        CarrosQueAlugueiPocketRecyclerAdapter adapter = new
                CarrosQueAlugueiPocketRecyclerAdapter(view.getContext(), dao.obterCarrosAlugados(), this);
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public List<CarroXUserAlugado> obterCarrosAlugados() {
        return dao.obterCarrosAlugados();
    }
}
