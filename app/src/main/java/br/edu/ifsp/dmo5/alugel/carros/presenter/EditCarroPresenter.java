package br.edu.ifsp.dmo5.alugel.carros.presenter;

import android.content.Intent;

import br.edu.ifsp.dmo5.alugel.carros.Constant.Constant;
import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.mvp.VeiculoCadastroMVP;
import br.edu.ifsp.dmo5.alugel.carros.view.edit_car_fim;
import br.edu.ifsp.dmo5.alugel.carros.view.new_car_fim;

public class EditCarroPresenter implements VeiculoCadastroMVP.Presenter {
    private VeiculoCadastroMVP.View view;

    public EditCarroPresenter(VeiculoCadastroMVP.View viewRecept) {
        view = viewRecept;
    }


    @Override
    public void continuar(Carro carro) {
        Intent intent = new Intent(view.getContext(), edit_car_fim.class);
        intent.putExtra(Constant.CARRO_MODEL, carro);
        view.getContext().startActivity(intent);
    }

    @Override
    public void deatach() {
        view = null;
    }
}
