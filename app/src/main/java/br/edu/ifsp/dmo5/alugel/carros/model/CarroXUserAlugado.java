package br.edu.ifsp.dmo5.alugel.carros.model;

public class CarroXUserAlugado {
    private int Id;
    private int carroId;

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    private String dataInicio;

    public int getCarroEntregue() {
        return carroEntregue;
    }

    public void setCarroEntregue(int carroEntregue) {
        this.carroEntregue = carroEntregue;
    }

    private int carroEntregue;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCarroId() {
        return carroId;
    }

    public void setCarroId(int carroId) {
        this.carroId = carroId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    private int userId;
    private String modeloCarro;
    private String foto;
    private String dataFim;


}
