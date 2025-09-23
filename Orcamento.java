// src/main/java/com/meuprojeto/modelo/Orcamento.java
package com.meuprojeto.modelo;

public class Orcamento {
    private String idOrcamento;
    private int mes;
    private int ano;
    private String idUsuario; // Chave estrangeira

    public Orcamento() {
    }

    public Orcamento(String idOrcamento, int mes, int ano, String idUsuario) {
        this.idOrcamento = idOrcamento;
        this.mes = mes;
        this.ano = ano;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public String getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(String idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Orcamento{" +
               "idOrcamento='" + idOrcamento + '\'' +
               ", mes=" + mes +
               ", ano=" + ano +
               ", idUsuario='" + idUsuario + '\'' +
               '}';
    }
}
