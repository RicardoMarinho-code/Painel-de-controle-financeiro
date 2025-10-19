package meudb.top.ecommerce.DAO;
import java.sql.*;
public interface InterfaceConexao {
    Connection conectar();
    void fechar (Connection conexao);
}
