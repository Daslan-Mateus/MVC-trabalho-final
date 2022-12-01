package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaDeConexoes {

    private static FabricaDeConexoes instancia;

    private FabricaDeConexoes(){};

    public static FabricaDeConexoes obterInstancia(){
        if(instancia == null){
            instancia = new FabricaDeConexoes();
        }
        return instancia;
    }

    public Connection obterConexao(){
        try{
            String stringDeConexao = "jdbc:sqlite:listatarefas.db";
            Connection c = DriverManager.getConnection(stringDeConexao);
            return c;
        }catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("Erro ao criar conexão com o banco de dados.");
    }
}
