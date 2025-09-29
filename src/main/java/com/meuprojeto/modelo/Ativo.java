package com.meuprojeto.modelo;

import java.math.BigDecimal;

/**
 * Representa um ativo financeiro do usuário.
 */
public class Ativo {
    private String idAtivo;
    private String descricao;
    private BigDecimal valor;
    private String idUsuario;

    public Ativo() {
    }

    public Ativo(String idAtivo, String descricao, BigDecimal valor, String idUsuario) {
        this.idAtivo = idAtivo;
        this.descricao = descricao;
        this.valor = valor;
        this.idUsuario = idUsuario;
    }

    // --- Getters e Setters ---

    public String getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(String idAtivo) {
        this.idAtivo = idAtivo;
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