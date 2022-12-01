package Model;

public class ConcluirTarefa {


    private TarefaDAO dao;

    public ConcluirTarefa(){
        dao = new TarefaDAO();
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

}
