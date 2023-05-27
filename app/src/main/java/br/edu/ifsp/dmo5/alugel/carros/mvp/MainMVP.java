package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;

public interface MainMVP {
    interface View {
        Context getContext();
        void finViewById();
        void setOnCLick();
    }
    interface Presenter {
        void deatach();
        boolean realizarLogin();
        void openCadastroLocador();
        void openCadastroLocatario();
    }
}
