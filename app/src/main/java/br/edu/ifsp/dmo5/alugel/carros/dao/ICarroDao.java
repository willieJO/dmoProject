package br.edu.ifsp.dmo5.alugel.carros.dao;

import java.util.List;

import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.model.User;

public interface ICarroDao {

    boolean create(Carro carro);
    List<Carro> findAll();
    void excluirCarro(Carro carro);
    void edit(Carro carro);
}
