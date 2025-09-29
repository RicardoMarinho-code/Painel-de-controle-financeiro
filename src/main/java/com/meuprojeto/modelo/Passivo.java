package com.meuprojeto.modelo;

import java.math.BigDecimal;

public class Passivo {
    private String idPassivo;
    private String descricao;
    private BigDecimal valor;
    private String idUsuario;

    public Passivo() {
    }

    public Passivo(String idPassivo, String descricao, BigDecimal valor, String idUsuario) {
        this.idPassivo = idPassivo;
        this.descricao = descricao;
        this.valor = valor;
        this.idUsuario = idUsuario;
    }

    // --- Getters e Setters ---

    public String getIdPassivo() {
        return idPassivo;
    }

    public void setIdPassivo(String idPassivo) {
        this.idPassivo = idPassivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getIdUsuario() { return idUsuario; }

    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
}