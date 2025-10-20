package meudb.top.financeiro.MODEL;

import java.math.BigDecimal;

public class Ativo {
    private String id;
    private String descricao;
    private BigDecimal valor;
    private String idUsuario;

    public Ativo(String id, String descricao, BigDecimal valor, String idUsuario) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.idUsuario = idUsuario;
    }

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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Ativo [ID=" + id + ", Descrição=" + descricao + ", Valor=" + valor + "]";
    }
}