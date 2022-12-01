package Model;

import java.sql.Connection;

public class BaseDAO {
    protected Connection obterConexao(){
        return FabricaDeConexoes.obterInstancia().obterConexao();
    }
}
