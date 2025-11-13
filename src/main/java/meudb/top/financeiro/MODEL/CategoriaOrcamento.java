package meudb.top.financeiro.MODEL;

import java.math.BigDecimal;

public class CategoriaOrcamento {
    private String id;
    private String nome;
    private BigDecimal valorPlanejado;
    private BigDecimal valorRealizado;
    private String idOrcamento;

    public CategoriaOrcamento(String id, String nome, BigDecimal valorPlanejado, BigDecimal valorRealizado, String idOrcamento) {
        this.id = id;
        this.nome = nome;
        this.valorPlanejado = valorPlanejado;
        this.valorRealizado = valorRealizado;
        this.idOrcamento = idOrcamento;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getValorPlanejado() { return valorPlanejado; }
    public void setValorPlanejado(BigDecimal valorPlanejado) { this.valorPlanejado = valorPlanejado; }
    public BigDecimal getValorRealizado() { return valorRealizado; }
    public void setValorRealizado(BigDecimal valorRealizado) { this.valorRealizado = valorRealizado; }
    public String getIdOrcamento() { return idOrcamento; }
    public void setIdOrcamento(String idOrcamento) { this.idOrcamento = idOrcamento; }

    public BigDecimal getSaldo() {
        return valorPlanejado.subtract(valorRealizado);
    }

    @Override
    public String toString() {
        return String.format("Categoria [ID=%s, Nome=%s, Planejado=%.2f, Realizado=%.2f, Saldo=%.2f]",
                id, nome, valorPlanejado, valorRealizado, getSaldo());
    }
}