package br.edu.ifsp.dmo5.alugel.carros.presenter;

import br.edu.ifsp.dmo5.alugel.carros.mvp.CadastroMVP;

import br.edu.ifsp.dmo5.alugel.carros.mvp.MainMVP;

public class LocadorPresenter implements CadastroMVP.Presenter {
    private CadastroMVP.View view;

    public LocadorPresenter(CadastroMVP.View view) {
        this.view = view;
    }

    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public boolean realizarCadastro() {
        view.showToast("Cadastro realizado com sucesso");
        return false;
    }


}
