package Controller;

import Model.Tarefa;
import Model.TarefaDAO;

import java.util.List;
public class ListarTarefas {
    private TarefaDAO dao;

    public ListarTarefas(){
        dao = new TarefaDAO();
    }

    public List<Tarefa> obterTodos(){
        return dao.obterTodos();
    }
}
