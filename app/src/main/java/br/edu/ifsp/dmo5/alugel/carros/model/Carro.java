package br.edu.ifsp.dmo5.alugel.carros.model;

import android.os.Parcelable;

import java.io.Serializable;

public class Carro implements Serializable {
    public String id;
    public String idDonoCarro;
    public String marca;
    public String modelo;
    public String cor;
    public String placa;
    public String tipoCombustivel;
    public String combustivel;
    public String arCondicionado;
    public String eletrico;
    public String porta;
    public String radio;
    public String quilometragem;
    public String crlve;
    public String cpf;
    public String foto;

    public Carro(){}

    public Carro(String id, String idDonoCarro, String marca, String modelo, String cor, String placa, String tipoCombustivel, String combustivel, String arCondicionado, String eletrico, String porta, String radio, String quilometragem, String crlve, String cpf, String foto) {
        this.id = id;
        this.idDonoCarro = idDonoCarro;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.tipoCombustivel = tipoCombustivel;
        this.combustivel = combustivel;
        this.arCondicionado = arCondicionado;
        this.eletrico = eletrico;
        this.porta = porta;
        this.radio = radio;
        this.quilometragem = quilometragem;
        this.crlve = crlve;
        this.cpf = cpf;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }



    public String getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(String quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getCrlve() {
        return crlve;
    }

    public void setCrlve(String crlve) {
        this.crlve = crlve;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(String arCondicionado) {
        this.arCondicionado = arCondicionado;
    }

    public String getEletrico() {
        return eletrico;
    }

    public void setEletrico(String eletrico) {
        this.eletrico = eletrico;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }
}
