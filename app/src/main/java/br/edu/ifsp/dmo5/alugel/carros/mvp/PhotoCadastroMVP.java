package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;
import android.os.Bundle;

public interface PhotoCadastroMVP {
    interface View {
        Context getContext();
        Bundle getBundle();
        String retornarBase64();
        void setMenu();
    }
    interface  Presenter {
        void salvarVeiculo();
        void deatach();
    }
}
