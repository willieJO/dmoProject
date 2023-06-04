package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;
import android.os.Bundle;

import br.edu.ifsp.dmo5.alugel.carros.model.Carro;

public interface VeiculoCadastroMVP {
    interface  View {
        Context getContext();
        void setMenu();
        void findElement();
        void click();
        Bundle getBundle();
        void showToast(String mensagem);
    }
    interface  Presenter {
        void continuar(Carro carro);
        void deatach();
    }
}
