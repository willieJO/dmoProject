package br.edu.ifsp.dmo5.alugel.carros.utils;

public class UserSeason {
    private static UserSeason instance;
    private int userId;
    public static synchronized UserSeason getInstance() {
        if (instance == null) {
            instance = new UserSeason();
        }
        return instance;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
