package br.edu.ifsp.dmo5.alugel.carros.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.dmo5.alugel.carros.model.CarroXUserAlugado;

public interface CarrosQueAlugueiMVP {
    interface View {
        Context getContext();
        void setToolbar();
        void finViewById();
        void setOnCLick();
        void showToast(String mensagem);
    }
    interface Presenter {
        void deatach();
        void entregar(CarroXUserAlugado carro);
        void cancelar(CarroXUserAlugado carro);
        void populateList(RecyclerView view);
        List<CarroXUserAlugado> obterCarrosAlugados();
    }
}
