package meudb.top.financeiro.MODEL;

public class Orcamento {
    private String id;
    private int mes;
    private int ano;
    private String idUsuario;

    public Orcamento(String id, int mes, int ano, String idUsuario) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public int getMes() { return mes; }
    public void setMes(int mes) { this.mes = mes; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    @Override
    public String toString() {
        return "Orcamento [ID=" + id + ", Mes=" + mes + ", Ano=" + ano + "]";
    }
}
