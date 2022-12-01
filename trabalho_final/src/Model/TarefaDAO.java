package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO extends BaseDAO{

    public void inserir(Tarefa tarefa) {
        String sql = "insert into tarefa(descricao, concluido) values(? ,?)";
        try(Connection c = obterConexao();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setString(1,tarefa.getDescricao());
            p.setBoolean(2,tarefa.isConcluido());
            p.execute();
        }catch (SQLException e){
            System.out.println("Erro ao inserir tarefa");
            e.printStackTrace();
        }
    }

    public void atualizar(Tarefa tarefa) {
        String sql = "update  tarefa set concluido = ?, descricao = ? where id =  ?";
        try(Connection c = obterConexao();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setBoolean(1,tarefa.isConcluido());
            p.setString(2,tarefa.getDescricao());
            p.setInt(3,tarefa.getId());
            p.execute();
        }catch (SQLException e){
            System.out.println("Erro ao atualizar tarefa ");
            e.printStackTrace();
        }
    }

    public void excluir(Tarefa tarefa){
        String sql = "delete from tarefa where id = ? and descricao = ? and concluido = ?";
        try(Connection c = obterConexao();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setInt(1,tarefa.getId());
            p.setString(2,tarefa.getDescricao());
            p.setBoolean(3,tarefa.isConcluido());
            p.execute();
        }catch (SQLException e){
            System.out.println("Erro ao atualizar tarefa ");
            e.printStackTrace();
        }
    }

    public List<Tarefa> obterTodos() {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "select id, descricao, concluido from tarefa";
        try(Connection c = obterConexao();
            PreparedStatement p = c.prepareStatement(sql)){

            ResultSet resultSet = p.executeQuery();
            while(resultSet.next()){
                Tarefa tarefa = new Tarefa();
                tarefa.setId(resultSet.getInt("id"));
                tarefa.setConcluido(resultSet.getBoolean("concluido"));
                tarefa.setDescricao(resultSet.getString("descricao"));

                lista.add(tarefa);
            }
        }catch (SQLException e){
            System.out.println("Erro ao obter todas as tarefas ");
            e.printStackTrace();
        }
        return lista;
    }
}
