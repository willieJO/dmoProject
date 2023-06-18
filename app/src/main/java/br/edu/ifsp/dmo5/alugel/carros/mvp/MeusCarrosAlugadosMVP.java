package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import br.edu.ifsp.dmo5.alugel.carros.model.Carro;

public interface MeusCarrosAlugadosMVP {
    interface View {
        Context getContext();
        Bundle getBundle();
        String retornarBase64();
        Carro getCarro();
        void setMenu();
    }
    interface Presenter {
        void editVeiculo(Carro carro);
        void salvarEdicaoVeiculo(Carro carro);
        void excluirVeiculo(Carro carro);
        void deatach();
        void populateList(RecyclerView recyclerView);
    }
}
