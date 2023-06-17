package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;

public interface CarroDetailsMVP {
    interface View {
        Context getContext();
        void setMenu();
        void finViewById();
        void setOnCLick();
        void showToast(String mensagem);
        void setDados();
    }
    interface Presenter {
        void deatach();
        void alugaCarro(int id);
    }
}
