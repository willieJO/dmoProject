package br.edu.ifsp.dmo5.alugel.carros.dao;

import java.util.List;

import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.model.CarroXUserAlugado;

public interface ICarroXUser {
    void alugar(int id, String data,String dataFim);
    List<CarroXUserAlugado> obterCarrosAlugados();
    void entregar(CarroXUserAlugado carro);
    void cancelar(CarroXUserAlugado carro);
}
