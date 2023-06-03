package br.edu.ifsp.dmo5.alugel.carros.dao;

import br.edu.ifsp.dmo5.alugel.carros.model.User;

public interface IUserDao {
    boolean create(User locador);
    boolean realizarLogin(String user, String pass);
}
