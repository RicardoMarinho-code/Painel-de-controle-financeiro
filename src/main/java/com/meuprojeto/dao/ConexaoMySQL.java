package meudb.top.ecommerce.DAO;
import java.sql.*;

public class ConexaoMySQL implements InterfaceConexao {
        private  static  final  String url = "jdbc:mysql://localhost:3307/ecommerce";
        private  static  final  String Usuario = "root";
        private  static  final  String Senha = "db_senha";

        @Override
        public Connection conectar(){
            try{
                return DriverManager.getConnection(url,Usuario,Senha);

            }catch (SQLException e){
                System.out.println("Erro");
                return null;
            }

        }
       @Override
        public  void fechar (Connection conexao)
       {
            try
            {
                if (conexao != null && !conexao.isClosed())
                {
                    conexao.close();
                    System.out.println("Conection Fechado.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao fechar.");
            }
        }

}


