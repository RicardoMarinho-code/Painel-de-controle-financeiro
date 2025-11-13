package meudb.top.financeiro.MODEL;

import java.math.BigDecimal;

public class Historico {
    private String id;
    private int mes;
    private int ano;
    private BigDecimal patrimonioLiquido;
    private String idUsuario;

    public Historico(String id, int mes, int ano, BigDecimal patrimonioLiquido, String idUsuario) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.patrimonioLiquido = patrimonioLiquido;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public BigDecimal getPatrimonioLiquido() { return patrimonioLiquido; }
    public void setPatrimonioLiquido(BigDecimal patrimonioLiquido) { this.patrimonioLiquido = patrimonioLiquido; }
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        return String.format("Historico [Mes/Ano=%d/%d, Patrimonio=%.2f]", mes, ano, patrimonioLiquido);
    }
}