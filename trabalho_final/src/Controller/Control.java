package Controller;

import Model.Tarefa;
import Model.TarefaDAO;

public class Control {

    private TarefaDAO dao = new TarefaDAO();

    public void inserir(Tarefa tarefa){
        dao.inserir(tarefa);
    }

    public void atualizar(Tarefa tarefa){
        dao.atualizar(tarefa);
    }

    public void excluir(Tarefa tarefa){
        dao.excluir(tarefa);
    }

    public void concluir(Tarefa tarefa) throws Exception {
        if(tarefa.getId() < 1){
            throw  new Exception("A tarefa não possui um identificador válido.");
        }
        if(tarefa.isConcluido()){
            throw  new Exception("A tarefa "+tarefa.getId()+" já está concluída.");
        }
        tarefa.setConcluido(true);
        dao.atualizar(tarefa);
    }

    public void desconcluir(Tarefa tarefa) throws Exception{
        if(tarefa.getId() < 1){
            throw  new Exception("A tarefa não possui um identificador válido.");
        }
        if(!tarefa.isConcluido()){
            throw  new Exception("A tarefa "+tarefa.getId()+" não está concluída.");
        }
        tarefa.setConcluido(false);
        dao.atualizar(tarefa);
    }

}
