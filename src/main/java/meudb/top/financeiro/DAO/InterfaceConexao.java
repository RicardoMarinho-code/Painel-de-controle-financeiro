package meudb.top.financeiro.DAO;
import java.sql.Connection;
public interface InterfaceConexao {
    Connection conectar();
    void fechar (Connection conexao);
}
