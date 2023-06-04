package br.edu.ifsp.dmo5.alugel.carros.dao;

import br.edu.ifsp.dmo5.alugel.carros.model.Carro;
import br.edu.ifsp.dmo5.alugel.carros.model.User;

public interface ICarroDao {
    boolean salvarCarro();
    boolean create(Carro carro);
}
