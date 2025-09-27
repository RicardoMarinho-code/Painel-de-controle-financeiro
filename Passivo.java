package com.meuprojeto.modelo;

import java.math.BigDecimal;

public class Passivo {

    private String id;
    private String descricao;
    private BigDecimal valor;
    private String usuarioId;

    public Passivo() {
    }

    public Passivo(String id, String descricao, BigDecimal valor, String usuarioId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.usuarioId = usuarioId;
    }

    // --- Getters e Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUsuarioId() { return usuarioId; }

    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}