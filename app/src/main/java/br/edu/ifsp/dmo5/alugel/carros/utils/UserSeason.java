package br.edu.ifsp.dmo5.alugel.carros.utils;

import br.edu.ifsp.dmo5.alugel.carros.model.User;

public class UserSeason {
    private static UserSeason instance;
    private User user;
    public static synchronized UserSeason getInstance() {
        if (instance == null) {
            instance = new UserSeason();
        }
        return instance;
    }
    public User getUser() {
        return user;
    }
    public  void setUser(User usuario) {
        user = usuario;
    }


}
