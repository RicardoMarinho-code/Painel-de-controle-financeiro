package meudb.top.financeiro.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL implements InterfaceConexao {
        // trocar para 3306 caso for usar o localhost do proprio pc
        private  static  final  String url = "jdbc:mysql://localhost:3307/sistema_financeiro";
        private  static  final  String Usuario = "root";
        // troque a senha quando for rodar no netbeans
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
