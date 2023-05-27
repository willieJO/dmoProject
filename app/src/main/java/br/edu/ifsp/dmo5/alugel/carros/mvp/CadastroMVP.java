package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;

public interface CadastroMVP {
    interface  View {
        void setToolbar();
        void finViewById();
        void setOnCLick();
        Context getContext();
        void showToast(String mensagem);
    }
    interface Presenter {
        void deatach();
        boolean realizarCadastro();

    }
}
