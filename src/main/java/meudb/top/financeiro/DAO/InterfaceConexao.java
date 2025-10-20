package meudb.top.financeiro.DAO;
import java.sql.*;
public interface InterfaceConexao {
    Connection conectar();
    void fechar (Connection conexao);
}
