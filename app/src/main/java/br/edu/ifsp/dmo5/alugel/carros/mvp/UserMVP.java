package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

public interface UserMVP {
    interface View {
        Context getContext();
        void finViewById();
        void setOnCLick();
        void showToast(String mensagem);
    }
    interface Presenter {
        void deatach();
        void populateList(RecyclerView view);
    }
}
