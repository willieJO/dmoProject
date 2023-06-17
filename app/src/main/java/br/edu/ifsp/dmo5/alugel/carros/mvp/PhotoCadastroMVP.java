package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;
import android.os.Bundle;

import br.edu.ifsp.dmo5.alugel.carros.model.Carro;

public interface PhotoCadastroMVP {
    interface View {
        Context getContext();
        Bundle getBundle();
        String retornarBase64();
        Carro getCarro();
        void setMenu();
    }
    interface  Presenter {
        void salvarVeiculo();
        void deatach();
    }
}
