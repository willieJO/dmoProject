package br.edu.ifsp.dmo5.alugel.carros.presenter;

import android.content.Intent;

import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;
import br.edu.ifsp.dmo5.alugel.carros.view.NewLocadorActivity;
import br.edu.ifsp.dmo5.alugel.carros.view.NewLocatarioActivity;

public class MainPresenter  implements MainMVP.Presenter {
    private MainMVP.View view;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public boolean realizarLogin() {
        return false;
    }

    @Override
    public void openCadastroLocador() {
        Intent intent = new Intent(view.getContext(), NewLocadorActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openCadastroLocatario() {
        Intent intent = new Intent(view.getContext(), NewLocatarioActivity.class);
        view.getContext().startActivity(intent);
    }


}
