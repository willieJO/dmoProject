package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;

public interface MainMVP {
    interface View {
        Context getContext();
        void finViewById();
        void setOnCLick();
        void showToast(String mensagem);
    }
    interface Presenter {
        void deatach();
        void realizarLogin(String email, String pass, boolean manterConectado);
        void openCadastro();
    }
}
